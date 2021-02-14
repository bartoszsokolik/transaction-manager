FROM adoptopenjdk/openjdk11:latest

ADD build/libs/transaction-manager.jar transaction-manager.jar

RUN ln -s transaction-manager.jar service.jar

RUN apt-get update && apt-get install -y dos2unix

ADD ./run.sh /run.sh
RUN chmod +x /run.sh

ADD ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

RUN dos2unix ./run.sh && dos2unix ./wait-for-it.sh && apt-get --purge remove -y dos2unix && rm -rf /var/lib/apt/lists/*

CMD ["/run.sh"]
