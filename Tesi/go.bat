@echo off
start osagent
@echo running the naming server...
start nameserv -VBJprop ORBpropStorage=ns.properties >ns.ior 
@echo running the interface repository...
start irep RetiREP 

