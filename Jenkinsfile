pipeline {
    agent any
    
    tools {
        jdk 'java8'
        maven 'Maven3.6.0'
    }
  
    stages {
        stage ('Do mvn clean') {
            steps {
                sh "mvn clean"
            }
        }
        stage ('Git clone') {
            steps {
                echo "Подпуливаемся из репозитария тестов"
                git 'ssh://git@bitbucket:7999/rs/rig_space_qaa.git'
            }
        }
        stage ('Create docker container with database + import dump') {
            steps {
                echo "Запускаем скрипт по созданию контейнера + накатываем рабочий дамп БД"
                sh "./create_db_container.sh"
            }
        }
        stage ('Running Rigspace modules') {
            steps {
                echo "Запускаем инстанс Rigspace"
                sh "ssh qa@testing /home/qa/qaa/rigspace/authentication/authentication.sh -a restart >/dev/null 2>&1 &"
                sh "ssh qa@testing /home/qa/qaa/rigspace/admin/admin.sh -a restart >/dev/null 2>&1 &"
            }
        }
        stage ('Running BaseTests inside Selenoid') {
            steps {
                sleep(time:120,unit:"SECONDS")
                echo "Запускаем тесты внутри Селеноида"
                sh 'mvn test'
            }
            post {
                always {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
        stage ('Stopping Rigspace modules') {
            steps {
                echo "Остановка инстанса Rigspace"
                sh "ssh qa@testing /home/qa/qaa/rigspace/authentication/authentication.sh -a stop >/dev/null 2>&1 &"
                sh "ssh qa@testing /home/qa/qaa/rigspace/admin/admin.sh -a stop >/dev/null 2>&1 &"
            }
        }
        stage ('Stopping docker container with database + drop DB') {
            steps {
                echo "Схлопываем контейнер в БД + удаляем схему БД Rigspace"
                sh "./drop_db_container.sh"
            }
        }
    }
    post {
        always {
            script {
                echo "Отправка статуса на Почту dev"
                emailext to: 'a.stupin@tetra-soft.ru',
                subject: "[QAA/Tests] Результаты сборки",
                body: """
                    <img src=\"https://github.com/HappyQA/happyqa.github.io/blob/master/rigspace_logo.jpg?raw=true\"><br><br>
                    <br><b>Результаты тестов:</b><br>
                    <br>Номер сборки: <b>${BUILD_NUMBER}</b>
                    <br>Статус сборки: <b>${currentBuild.result}</b>
                    <br><b><a href=\"http://jenkins/job/JavaAutotestsforRSProject/${BUILD_NUMBER}/allure/\">Просмотреть отчет по тестам</a></b>
                    """,
                mimeType: 'text/html'
                echo "Отправка статуса в Slack - qa-automation-ci"
                sendRC(currentBuild.currentResult, "Rigspace", "qa")
            }
        }
    }
}

def sendRC(String buildResult, String projectName, String channel) {
    def tokensByChannel = [
            qa: 'CLXFS4RRR'
    ]
    if  ( buildResult == "SUCCESS" ) {
        slackSend color: "good", channel: tokensByChannel.get(channel), message: "Все тесты пройдены Отчеты - http://jenkins/job/JavaAutotestsforRSProject/${BUILD_NUMBER}/allure"
    }
    else if ( buildResult == "FAILURE" ) {
        slackSend color: "danger", channel: tokensByChannel.get(channel), message: "Есть провалившиеся тесты Отчеты - http://jenkins/job/JavaAutotestsforRSProject/${BUILD_NUMBER}/allure/"
    }
    else if ( buildResult == "UNSTABLE" ) {
        slackSend color: "warning", channel: tokensByChannel.get(channel), message: "Нестабильный результат - ${BUILD_URL}"
      }
}