pipeline {
    agent any
    tools {
        gradle '7.4.2'
    }

    stages {

        stage('Build') {
            steps {
                echo 'Building..'
                sh 'gradle build'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
