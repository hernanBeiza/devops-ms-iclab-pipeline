/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call(){
	pipeline {
		agent any

		parameters {
	    	choice(
		        name: 'paramHerramienta',
		        choices: "maven\ngradle",
		        description: 'Parámetro que determinará si se ejecuta maven.groovy o gradle.groovy'
	        )
	        string(
	        	name: 'paramRama',
	        	defaultValue: '',
	        	description: 'Ramas a ejecutar'
        	)
	        string(
	        	name: 'paramStage',
	        	defaultValue: '',
	        	description: 'Stage(s) a ejecutar'
        	)
		}

		stages {
			stage('Pipeline') {
				steps {
			      	script {
					    stage('iniciar') {
					    	echo "iniciar";
				    		String paramHerramienta = params.paramHerramienta.toLowerCase();
				    		String paramRama = params.paramRama.toLowerCase();
				    		String paramStage = params.paramStage;
							//def validaciones = load 'validaciones.groovy'
				    		if(validaciones.verificarRama(paramRama)){
				    			echo "Rama válida";
						    	if(validaciones.verificarHerramienta(paramHerramienta)){
						    		echo "Herramienta válida";
						    		if(paramHerramienta=="maven"){
						    			maven.call(paramStage);
						    		} else if(paramHerramienta=="gradle"){
						    			gradle.call(paramStage);
						    		}
					    		} else {
						    		echo "Herramienta no válida";
					    		}
					    	} else {
				    			echo "Rama no válida";
					    	}
					    }
		      		}
				}
	    	}
		}
	}
}

return this;