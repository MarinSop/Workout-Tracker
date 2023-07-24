    
        function selectCategory(categoryId) 
        {
            let allCategories = document.getElementsByClassName("exercise-select");
            Array.from(allCategories).forEach((element) => {
                element.hidden = true;
                if (element.id === categoryId) {
                  element.hidden = false;
                }
              });
        }

        function selectExercise(exerciseId,exerciseName)
        {
            let found = false;
            let allSelectedExercises = document.getElementsByClassName("selected-exercise");
            Array.from(allSelectedExercises).forEach((element) => {
                if (element.id == exerciseId) {
                    element.remove();
                    found = true;
                    return;
                }
              });
            if(found)
            {
                return;
            }
            let index = allSelectedExercises.length;
                let parent = document.querySelector('#selected-exercises');
                let newExercise = document.createElement("div");
                newExercise.setAttribute("id",exerciseId);
                newExercise.setAttribute("class","selected-exercise");
                parent.append(newExercise);

                let exerciseIdElement = document.createElement("input");
                exerciseIdElement.setAttribute("type","hidden");
                exerciseIdElement.setAttribute("name","exercises["+ index +"].id");
                exerciseIdElement.setAttribute("value", exerciseId);
                newExercise.append(exerciseIdElement);

                let labelForName = document.createElement("label");
                labelForName.innerText = "Name";
                newExercise.appendChild(labelForName);
                let exerciseNameElement = document.createElement("span");
                exerciseNameElement.innerText = exerciseName;
                newExercise.append(exerciseNameElement);

                let labelForReps = document.createElement("label");
                labelForReps.innerText = "Reps";
                newExercise.appendChild(labelForReps);
                let exerciseSetsElement = document.createElement("input");
                exerciseSetsElement.setAttribute("type","number");
                exerciseSetsElement.setAttribute("name","exercises["+ index +"].sets");
                exerciseSetsElement.setAttribute("min","0");
                newExercise.append(exerciseSetsElement);

                let labelForSets = document.createElement("label");
                labelForSets.innerText = "Sets";
                newExercise.appendChild(labelForSets);
                let exerciseRepsElement = document.createElement("input");
                exerciseRepsElement.setAttribute("type","number");
                exerciseRepsElement.setAttribute("name","exercises["+ index +"].reps");
                exerciseRepsElement.setAttribute("min","0");
                newExercise.append(exerciseRepsElement);

                let labelForWeight = document.createElement("label");
                labelForWeight.innerText = "Weight";
                newExercise.appendChild(labelForWeight);
                let exerciseWeightElement = document.createElement("input");
                exerciseWeightElement.setAttribute("type","number");
                exerciseWeightElement.setAttribute("name","exercises["+ index +"].weight");
                exerciseWeightElement.setAttribute("min","0");
                newExercise.append(exerciseWeightElement);
        }