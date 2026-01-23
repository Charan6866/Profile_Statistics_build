
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run TestNG Tests') {
            steps {
                // Running TestNG using your testNg.xml
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=testNg.xml'
            }
        }

        stage('Publish Reports') {
            steps {
                // Surefire/JUnit XML
                junit 'target/surefire-reports/*.xml'

                // Extent reports + Screenshots + Logs
                archiveArtifacts artifacts: 'reports/**, screenshots/**, logs/**', fingerprint: true

                // Allure results folder
                allure results: [[path: 'allure-results']]
            }
        }
    }
}
