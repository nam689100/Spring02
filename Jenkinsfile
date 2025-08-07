pipeline {
    agent any
    
    tools {
        maven 'my-maven'  // 젠킨스에서 설치한 이름
    }

    stages {
        stage('0. 연결 확인') { steps { echo '스테이지 출발' } }
        
        stage('1. Build') {
            steps {
                echo 'Maven으로 빌드 시작'
                sh 'mvn clean package'
            }
        }        
    }
}
