
function selectCategory(categoryId) {
  let allCategories = document.getElementsByClassName("exercise-select");
  Array.from(allCategories).forEach((element) => {
    element.hidden = true;
    if (element.id === categoryId) {
      element.hidden = false;
    }
  });
}

function selectExercise(exerciseId, exerciseName) {
  let found = false;
  let allSelectedExercises = document.getElementsByClassName("selected-exercise");
  Array.from(allSelectedExercises).forEach((element) => {
    if (element.id == exerciseId) {
      found = true;
      return;
    }
  });
  if (found) {
    return;
  }
  let index = allSelectedExercises.length;
  let parent = document.querySelector('tbody');
  let newExercise = document.createElement("tr");
  newExercise.setAttribute("id", exerciseId);
  newExercise.setAttribute("class", "selected-exercise");
  parent.append(newExercise);

  let exerciseIdElement = document.createElement("input");
  exerciseIdElement.setAttribute("type", "hidden");
  exerciseIdElement.setAttribute("name", "exercises[" + index + "].id.exerciseId");
  exerciseIdElement.setAttribute("value", exerciseId);

  let tdName = document.createElement("td");
  let exerciseNameElement = document.createElement("input");
  exerciseNameElement.setAttribute("type", "text");
  exerciseNameElement.setAttribute("value", exerciseName);
  exerciseIdElement.readOnly = true;
  tdName.append(exerciseIdElement);
  tdName.append(exerciseNameElement);
  newExercise.append(tdName);

  let tdReps = document.createElement("td");
  let exerciseRepsElement = document.createElement("input");
  exerciseRepsElement.setAttribute("type", "number");
  exerciseRepsElement.setAttribute("name", "exercises[" + index + "].reps");
  exerciseRepsElement.setAttribute("value", "0");
  exerciseRepsElement.setAttribute("min", "0");
  tdReps.append(exerciseRepsElement);
  newExercise.append(tdReps);

  let tdSets = document.createElement("td");
  let exerciseSetsElement = document.createElement("input");
  exerciseSetsElement.setAttribute("type", "number");
  exerciseSetsElement.setAttribute("name", "exercises[" + index + "].sets");
  exerciseSetsElement.setAttribute("value", "0");
  exerciseSetsElement.setAttribute("min", "0");
  tdSets.append(exerciseSetsElement);
  newExercise.append(tdSets);

  let tdWeight = document.createElement("td");
  let exerciseWeightElement = document.createElement("input");
  exerciseWeightElement.setAttribute("type", "number");
  exerciseWeightElement.setAttribute("name", "exercises[" + index + "].weight");
  exerciseWeightElement.setAttribute("value", "0");
  exerciseWeightElement.setAttribute("min", "0");
  tdWeight.append(exerciseWeightElement);
  newExercise.append(tdWeight);

  let tdDelete = document.createElement("td");
  let deleteButton = document.createElement("input");
  deleteButton.setAttribute("type","button");
  deleteButton.setAttribute("value","X");
  deleteButton.dataset.exerciseId = exerciseId;
  deleteButton.addEventListener("click", function(event) {
      const id = event.target.dataset.exerciseId;
      deleteExercise(id);
  });
  tdDelete.append(deleteButton);
  newExercise.append(tdDelete);
}

function deleteExercise(id) {
  let nodelist = document.querySelectorAll('.selected-exercise');
  let elem = Array.from(nodelist).find(e => e.id == id);
  elem.parentNode.removeChild(elem);
}
