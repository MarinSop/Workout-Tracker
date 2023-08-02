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
        let parent = document.querySelector('tbody');
        let newExercise = document.createElement("tr");
        newExercise.setAttribute("id",exerciseId);
        newExercise.setAttribute("class","exercise-group");
        parent.append(newExercise);

        let exerciseIdElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".exercise.id")
        exerciseIdElement.setAttribute("type","hidden");
        exerciseIdElement.setAttribute("name","workoutExercises["+ index +"].exercise.id");
        exerciseIdElement.setAttribute("value", exerciseId);

        let tdName = document.createElement("td");
        let divFormName = document.createElement("div");
        divFormName.setAttribute("class","form-group");
        let exerciseNameElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".exercise.name")
        exerciseNameElement.setAttribute("type","text");
        exerciseNameElement.setAttribute("name","workoutExercises["+ index +"].exercise.name");
        exerciseNameElement.setAttribute("value", exerciseName);
        exerciseNameElement.readOnly = true;
        divFormName.append(exerciseNameElement);
        divFormName.append(exerciseIdElement);
        tdName.append(divFormName);
        newExercise.append(tdName);

        let tdReps = document.createElement("td");
        let divFormReps = document.createElement("div");
        divFormReps.setAttribute("class","form-group");
        let exerciseRepsElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".reps")
        exerciseRepsElement.setAttribute("type","number");
        exerciseRepsElement.setAttribute("name","workoutExercises["+ index +"].reps");
        exerciseRepsElement.setAttribute("value","0");
        exerciseRepsElement.setAttribute("min","0");
        divFormReps.append(exerciseRepsElement);
        tdReps.append(divFormReps);
        newExercise.append(tdReps);

        let tdSets = document.createElement("td");
        let divFormSets = document.createElement("div");
        divFormSets.setAttribute("class","form-group");
        let exerciseSetsElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".sets")
        exerciseSetsElement.setAttribute("type","number");
        exerciseSetsElement.setAttribute("name","workoutExercises["+ index +"].sets");
        exerciseSetsElement.setAttribute("value","0");
        exerciseSetsElement.setAttribute("min","0");
        divFormSets.append(exerciseSetsElement);
        tdSets.append(divFormSets);
        newExercise.append(tdSets);

        let tdWeight = document.createElement("td");
        let divFormWeight = document.createElement("div");
        divFormWeight.setAttribute("class","form-group");
        let exerciseWeightElement = document.createElement("input");
        exerciseIdElement.setAttribute("id","workoutExercises"+index+".weight")
        exerciseWeightElement.setAttribute("type","number");
        exerciseWeightElement.setAttribute("name","workoutExercises["+ index +"].weight");
        exerciseWeightElement.setAttribute("value","0");
        exerciseWeightElement.setAttribute("min","0");
        divFormWeight.append(exerciseWeightElement);
        tdWeight.append(divFormWeight);
        newExercise.append(tdWeight);

        let tdDelete = document.createElement("td");
        let divFormDelete = document.createElement("div");
        divFormDelete.setAttribute("class","form-group");
        let deleteButton = document.createElement("input");
        deleteButton.setAttribute("type","button");
        deleteButton.setAttribute("value","X");
        deleteButton.dataset.exerciseId = exerciseId;
        deleteButton.addEventListener("click", function(event) {
            const id = event.target.dataset.exerciseId;
            deleteExercise(id);
        });
        divFormDelete.append(deleteButton);
        tdDelete.append(divFormDelete);
        newExercise.append(tdDelete);
}