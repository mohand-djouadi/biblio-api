pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage ('Checkout') {
            steps {
                checkout scm
                sh 'git log --oneline -5'
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
            githubNotify status: 'SUCCESS', description: 'pipeline completed', context: 'jenkins cicd'
        }
        failure {
            githubNotify status: 'FAILURE', description: 'pipeline failed', context: 'jenkins cicd'
        }
    }
}