FROM adoptopenjdk/openjdk11:latest

ADD build/libs/transaction-manager.jar transaction-manager.jar

RUN ln -s transaction-manager.jar service.jar

ADD ./run.sh /run.sh
RUN chmod +x /run.sh

ADD ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

CMD ["/run.sh"]
