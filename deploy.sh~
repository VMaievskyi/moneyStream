export SERVER_HOME="/home/slava/Documents/server/apache-tomcat-6.0.37"

export PROJECT_HOME="/home/slava/Documents/workspace/moneyStream"

export ARTIFACT="moneyStream-0.0.1-SNAPSHOT.war"

sh $SERVER_HOME/bin/shutdown.sh

rm -rf $SERVER_HOME/webapps/$ARTIFACT
cp $PROJECT_HOME/target/$ARTIFACT $SERVER_HOME/webapps

sh $SERVER_HOME/bin/startup.sh
