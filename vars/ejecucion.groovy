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
	        /*
	        string(
	        	name: 'paramRama',
	        	defaultValue: '',
	        	description: 'Ramas a ejecutar'
        	)
        	*/
	        string(
	        	name: 'paramStage',
	        	defaultValue: '',
	        	description: 'Stage(s) a ejecutar'
        	)
		}

		stages {
			stage('Validar') {
				steps {
			      	script {
				    	echo "iniciar"
				    	env.ALUMNO="Hernán Beiza";
						String paramHerramienta = params.paramHerramienta;
				    	echo "paramHerramienta ${paramHerramienta}";
						String paramStage = params.paramStage;
				    	echo "paramStage ${paramStage}";

				    	if(validaciones.verificarHerramienta(paramHerramienta)){}
				    	if(validaciones.verificarArchivoHerramienta(paramHerramienta)){}
		      		}
				}
	    	}
		}
	}
}

return this;