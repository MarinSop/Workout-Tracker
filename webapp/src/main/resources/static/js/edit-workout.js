function deleteExercise(id)
{
    let nodelist = document.querySelectorAll('.exercise-group');
    let elem = Array.from(nodelist).find(e => e.id == id);
    elem.parentNode.removeChild(elem);
}

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
    let allSelectedExercises = document.getElementsByClassName("exercise-group");
    Array.from(allSelectedExercises).forEach((element) => {
        if (element.id == exerciseId) {
            found = true;
            return;
        }
      });
    if(found)
    {
        return;
    }
        let index = allSelectedExercises.length;
        let parent = document.querySelector('.exercises');
        let newExercise = document.createElement("div");
        newExercise.setAttribute("id",exerciseId);
        newExercise.setAttribute("class","exercise-group");
        parent.append(newExercise);

        let exerciseIdElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".exercise.id")
        exerciseIdElement.setAttribute("type","hidden");
        exerciseIdElement.setAttribute("name","workoutExercises["+ index +"].exercise.id");
        exerciseIdElement.setAttribute("value", exerciseId);
        newExercise.append(exerciseIdElement);

        let labelForName = document.createElement("label");
        labelForName.innerText = "Name";
        newExercise.appendChild(labelForName);
        let exerciseNameElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".exercise.name")
        exerciseNameElement.setAttribute("type","text");
        exerciseNameElement.setAttribute("name","workoutExercises["+ index +"].exercise.name");
        exerciseNameElement.setAttribute("value", exerciseName);
        exerciseNameElement.readOnly = true;
        newExercise.append(exerciseNameElement);

        let labelForReps = document.createElement("label");
        labelForReps.innerText = "Reps";
        newExercise.appendChild(labelForReps);
        let exerciseSetsElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".reps")
        exerciseSetsElement.setAttribute("type","number");
        exerciseSetsElement.setAttribute("name","workoutExercises["+ index +"].reps");
        exerciseSetsElement.setAttribute("value","0");
        exerciseSetsElement.setAttribute("min","0");
        newExercise.append(exerciseSetsElement);

        let labelForSets = document.createElement("label");
        labelForSets.innerText = "Sets";
        newExercise.appendChild(labelForSets);
        let exerciseRepsElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".sets")
        exerciseRepsElement.setAttribute("type","number");
        exerciseRepsElement.setAttribute("name","workoutExercises["+ index +"].sets");
        exerciseRepsElement.setAttribute("value","0");
        exerciseRepsElement.setAttribute("min","0");
        newExercise.append(exerciseRepsElement);

        let labelForWeight = document.createElement("label");
        labelForWeight.innerText = "Weight";
        newExercise.appendChild(labelForWeight);
        let exerciseWeightElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".weight")
        exerciseWeightElement.setAttribute("type","number");
        exerciseWeightElement.setAttribute("name","workoutExercises["+ index +"].weight");
        exerciseWeightElement.setAttribute("value","0");
        exerciseWeightElement.setAttribute("min","0");
        newExercise.append(exerciseWeightElement);

        let deleteButton = document.createElement("input");
        deleteButton.setAttribute("type","button");
        deleteButton.setAttribute("value","X");
        deleteButton.dataset.exerciseId = exerciseId;
        deleteButton.addEventListener("click", function(event) {
            const id = event.target.dataset.exerciseId;
            deleteExercise(id);
        });
        newExercise.append(deleteButton);
}