echo "Building core..."
cd core
mvn -q clean install
echo "Building rest..."
cd ../rest
mvn -q clean package
if [ ! -z "$1" ]; then
    echo "Copying jar to $1"
    cp target/rest-1.0-jar-with-dependencies.jar $1
fi

