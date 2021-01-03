def verificarStages(tipo, paramStages){
	echo "verificarStages ${tipo} ${paramStages}";
	//Dependerá del tipo de pipeline, CD o CI
}

def verificarHerramienta(paramHerramienta){
	echo "paramHerramienta ${paramHerramienta}";
	env.ALUMNO="Hernán Beiza";
	if(paramHerramienta=="maven"){
		env.BUILD_TOOL="MAVEN";
		echo "MAVEN soportado";
		//maven.call();
		return true;
	} else if (paramHerramienta=="gradle"){
		env.BUILD_TOOL="GRADLE";
		echo "GRADLE soportado";
		//Recordar que ahora los archivos están en vars
		//gradle.call();
		return true;
	} else {
		echo "Herramienta de construcción no soportada";
		return false;
	}
}

def verificarArchivoHerramienta(paramHerramienta){
	sh "ls -l"
	if(paramHerramienta=="maven"){
		if (fileExists('pom.xml')) {
			echo "Archivo pom.xml existe";
			return true;
		} else {
			echo "Archivo pom.xml no existe";
			return false;
		}
	} else if(paramHerramienta=="gradle"){
		if (fileExists('settings.gradle')) {
			echo "Archivo settings.gradle existe";
			return true;
		} else {
			echo "Archivo settings.gradle no existe";
			return false;
		}
	} else {
		echo "Tipo de herramienta no soportada";
		return false;
	}
}

def verificarRama(){
	echo "verificarRama";
	def rama = BRANCH_NAME;
	echo "NOMBRE RAMA: ${rama}";
	if(rama=="main" || rama=="master"){
		echo "Rama MAIN bloqueada";
		return false;
	} else {
		return true;
	}
}

def obtenerTipoDeRama(){
	echo "obtenerTipoDeRama";
	def rama = BRANCH_NAME;
	def tipo = "";
	if(rama.contains("FEATURE")){
		tipo = "FEATURE"
	} else if(rama.contains("DEVELOP")){
		tipo "DEVELOP";
	} else if(rama.contains("RELEASE")){
		tipo "RELEASE";
	} else {
		return "RAMA NO SOPORTADA"
	}
	print tipo;
	return tipo;
}

return this;