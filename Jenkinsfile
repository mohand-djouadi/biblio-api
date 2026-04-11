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
        RENDER_SERVICE = credentials('render_sunlib_id')
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
                    withCredentials([string(credentialsId: 'render_api_key', variable: 'RENDER_API_KEY')]) {
                        def response = httpRequest(
                            url: "https://api.render.com/deploy/${env.RENDER_SERVICE}?key=${RENDER_API_KEY}",
                            httpMode: 'POST',
                            validResponseCodes: '200:299'
                        )
                    echo "Render response status: ${response.status}"
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