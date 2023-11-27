### VARIABLES ###
CHEMIN=fr/iutfbleau/proalbouzonSAE31_2023
JAR = jar
JARNAME = proalbouzonSAE31_2023.jar
JC = javac -d build -sourcepath src src/${CHEMIN}/*.java
JCFLAGS = -encoding UTF-8 -implicit:none
JVM = java
JVMFLAGS = -cp ".:/export/documents/mariadb-client.jar":build
### RULES COMPILATION ###
airaime :
	rm -rf res
	rm -rf build

creationres :
	mkdir -p res/maria

dezip :
	unzip -q ./mariadb-client.jar -d res/maria

creationbuild :
	mkdir -p build/${CHEMIN}

creationjaruser :
	${JAR} cvfe ${JARNAME} fr.iutfbleau.proalbouzonSAE31_2023.MainUser -C build fr -C res/maria .

creationjardev :
	${JAR} cvfe ${JARNAME} fr.iutfbleau.proalbouzonSAE31_2023.MainDev -C build fr -C res/maria .





build/${CHEMIN}/MainUser.class : src/${CHEMIN}/MainUser.java build/${CHEMIN}/Fenetre.class
	${JC} ${JCFLAGS} src/${CHEMIN}/MainUser.java

build/${CHEMIN}/Fenetre.class : src/${CHEMIN}/Fenetre.java build/${CHEMIN}/Proto.class build/${CHEMIN}/Modele.class build/${CHEMIN}/Detect.class build/${CHEMIN}/BaseDonnees.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Fenetre.java

build/${CHEMIN}/Modele.class : src/${CHEMIN}/Modele.java build/${CHEMIN}/Element.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Modele.java

build/${CHEMIN}/BaseDonnees.class : src/${CHEMIN}/BaseDonnees.java
	${JC} ${JCFLAGS} src/${CHEMIN}/BaseDonnees.java

build/${CHEMIN}/Detect.class : src/${CHEMIN}/Detect.java build/${CHEMIN}/Element.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Detect.java

build/${CHEMIN}/Proto.class : src/${CHEMIN}/Proto.java
	${JC} ${JCFLAGS} src/${CHEMIN}/Proto.java

build/${CHEMIN}/Element.class : src/${CHEMIN}/Element.java
	${JC} ${JCFLAGS} src/${CHEMIN}/Element.java





build/${CHEMIN}/MainDev.class : src/${CHEMIN}/MainDev.java build/${CHEMIN}/Info.class
	${JC} ${JCFLAGS} src/${CHEMIN}/MainUser.java

build/${CHEMIN}/Info.class : src/${CHEMIN}/Info.java build/${CHEMIN}/BaseInfo.class build/${CHEMIN}/Camembert.class build/${CHEMIN}/ProtoDev.class build/${CHEMIN}/ControlDev.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Info.java

build/${CHEMIN}/BaseInfo.class : src/${CHEMIN}/BaseInfo.java
	${JC} ${JCFLAGS} src/${CHEMIN}/BaseInfo.java

build/${CHEMIN}/ProtoDev.class : src/${CHEMIN}/ProtoDev.java build/${CHEMIN}/BaseInfo.class
	${JC} ${JCFLAGS} src/${CHEMIN}/Protodev.java

build/${CHEMIN}/Camembert.class : src/${CHEMIN}/Camembert.java
	${JC} ${JCFLAGS} src/${CHEMIN}/Camembert.java

build/${CHEMIN}/ControlDev.class : src/${CHEMIN}/ControlDev.java
	${JC} ${JCFLAGS} src/${CHEMIN}/ControlDev.java


### RULES EXECUTION ###
user : airaime creationres dezip creationbuild build/${CHEMIN}/MainUser.class creationjaruser
	${JVM} -jar ${JARNAME}
###	${JVM} ${JVMFLAGS} fr.iutfbleau.proalbouzonSAE31_2023.MainUser ###

dev : airaime creationres dezip creationbuild build/${CHEMIN}/MainDev.class creationjardev
	${JVM} -jar ${JARNAME}
###	${JVM} ${JVMFLAGS} fr.iutfbleau.proalbouzonSAE31_2023.MainDev
	


clean :
	-rm -f build/${CHEMIN}/*.class
mrproper : clean build/${CHEMIN}/MainUser.class
### BUTS FACTICES ###
.PHONY : run clean mrproper
### FIN ###