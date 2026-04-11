pipeline {
    agent any

    triggers {
        githubPush()
    }

    options {
        skipDefaultCheckout(true)
    }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        RENDER_API_KEY = credentials('render_api_key')
        RENDER_SUNLIB_HOOK = "https://api.render.com/deploy/srv-d2qe1hndiees73csodl0?key=$RENDER_API_KEY"
    }

    stages {
        stage ('Checkout') {
            steps {
                script {
                    def scmVars = checkout scm
                    env.GIT_COMMIT_REV = scmVars.GIT_COMMIT
                    sh 'git log --oneline -5'
                    echo "${env.GIT_COMMIT_REV}"
                    githubNotify(
                        credentialsId: 'github_token',
                        account: 'mohand-djouadi',
                        repo: 'biblio-api',
                        sha: "${env.GIT_COMMIT_REV}",
                        status: 'PENDING',
                        context: 'jenkins/cicd'
                    )
                }
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
        stage ('deploye to Render') {
            steps {
                script {
                    echo "deploying to render ........."
                    def response = httpRequest(
                        url: "$RENDER_SUNLIB_HOOK",
                        httpMode: 'POST',
                        validResponseCode: '200:299'
                    )
                }
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
                    sha: "${env.GIT_COMMIT_REV}",
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
                    sha: "${env.GIT_COMMIT_REV}",
                    status: 'FAILURE',
                    context: 'jenkins/cicd'
                )
            }
        }
    }
}