# aisera
Running the application locally

java and maven need to be installed on the system

Then run it : mvn -q clean compile exec:java -Dexec.mainClass=com.aisera.ParserApplication -Dexec.args="small-user-data.json small-user-data2.json"

Above the path of the files to be processed is passed as arguments separated with space
