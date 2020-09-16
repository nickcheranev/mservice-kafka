user/1

## zookeeper

wget http://apache-mirror.rbc.ru/pub/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
tar -zxf zookeeper-*.tar.gz -C /usr/local
mv -f /usr/local/zookeeper-* /usr/local/zookeeper
mkdir -p /home/zookeeper/data
cp /usr/local/zookeeper/conf/zoo_sample.cfg /usr/local/zookeeper/conf/zoo.cfg

Настройка /usr/local/zookeeper/conf/zoo.cfg
tickTime=2000
initLimit=5
syncLimit=2
dataDir=/home/zookeeper/data
clientPort=2181

Запуск
sudo /usr/local/zookeeper/bin/zkServer.sh start

Проверка
/usr/local/zookeeper/bin/zkServer.sh status

Останов
sudo /usr/local/zookeeper/bin/zkServer.sh stop

Запуск клиента для проверки
/usr/local/zookeeper/bin/zkCli.sh
help

## kafka

в server.properties добавить параметры
delete.topic.enable=true
auto.create.topic.enable=true

cd /home/user
wget http://mirror.linux-ia64.org/apache/kafka/2.6.0/kafka_2.13-2.6.0.tgz
tar -zxvf kafka_2.13-2.6.0.tgz

Запуск
cd kafka_2.13-2.6.0
bin/kafka-server-start.sh config/server.properties

если ошибка со стартом про логи
rm -r /tmp/kafka-logs
sudo rm -r /home/zookeeper/data

Останов
bin/kafka-server-stop.sh

Создание темы demo
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic demo

Создание производителя в режиме ожидания сообщений
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic demo
>{"id":"1","message":"World"}

Создание прослушивателя
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo --from-beginning

Создание производителя и передача сообщения {"id":"1","message":"World"} в тему demo
echo "{\"id\":\"1\",\"message\":\"World\"}" | bin/kafka-console-producer.sh --broker-list localhost:9092 --topic demo > /dev/null
