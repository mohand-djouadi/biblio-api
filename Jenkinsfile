pipeline {
    agent any
    triggers {
        githubPush()
    }
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage ('Checkout') {
            steps {
                checkout scm
                sh 'git log --oneline -5'
            }
        }
        stage('Check Java') {
            steps {
                sh 'java -version'
                sh 'javac -version'
                sh 'mvn -version'
                sh 'echo $JAVA_HOME'
            }
        }
        stage ('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
    post {
        success {
            script {
                githubNotify(
                    credentialsId: 'github_token',
                    account: 'mohand-djouadi',
                    repo: 'biblio-api',
                    sha: "${env.GIT_COMMIT}",
                    status: 'SUCCESS',
                    context: 'jenkins/cicd'
                )
            }
        }
        failure {
            script {
                githubNotify(
                    credentialsId: 'github_token',
                    account: 'mohand-djouadi',
                    repo: 'biblio-api',
                    sha: "${env.GIT_COMMIT}",
                    status: 'FAILURE',
                    context: 'jenkins/cicd'
                )
            }
        }
    }
}