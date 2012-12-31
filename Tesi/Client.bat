@echo off
@echo starting the client...
@echo start vbj -Dvbroker.repository.name=RetiREP -DORBInitRef=NameService=corbaloc::localhost:9999/NameService Reti.Client 
start vbj -Dvbroker.repository.name=RetiREP -DORBInitRef=NameService=corbaloc::localhost:9999/NameService Reti.Client 