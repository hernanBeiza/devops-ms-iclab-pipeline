def verificarHerramienta(paramHerramienta){
	echo "paramHerramienta ${paramHerramienta}";
	env.ALUMNO="Hernán Beiza";
	if(paramHerramienta=="maven"){
		env.BUILD_TOOL="MAVEN";
		//maven.call();
		return true;
	} else if (paramHerramienta=="gradle"){
		env.BUILD_TOOL="GRADLE";
		//Recordar que ahora los archivos están en vars
		//gradle.call();
		return true;
	} else {
		echo "Herramienta de construcción no soportada";
		return false;
	}
}

def verificarRama(paramRama){
	echo "paramRama ${paramRama}";
	if(!paramRama.isEmpty()){
		if(paramRama=="main" || paramRama=="master"){
			return false;
		} else {
			return true;
		}		
		/*
		def prueba = sh (script:"git branch --list ${paramRama}", returnStdout: true).trim();
		println prueba;

		def existe = sh (script:"git show-ref refs/heads/${paramRama}", returnStdout: true).trim();
		println "Rama existe: ${existe}";
		if(existe.isEmpty()){
			echo "No existe";
			return false;
		} else {
			echo "Existe ${existe}";
			return true;
		}
		*/
	} else {
		return false;
	}
}

return this;