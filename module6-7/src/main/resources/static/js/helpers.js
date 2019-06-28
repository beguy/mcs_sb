function synchronizeSelectId() {
    var selects = document.getElementsByClassName("selectWithSynchronizedId");
    selects = Array.from(selects); //convert to array

    selects.forEach(select => {
        //get id from classname: selected=#id
        let classNames = Array.from(select.classList);
        let selectedClassNamePosition = classNames.findIndex(className => className.includes("selected"));
        let id = classNames[selectedClassNamePosition].split("=")[1];
        //find option position from value
        let options = Array.from(select.options);
        let optionIndex = options.map(option => option.value)
            .findIndex(value => (value == id) ? true : false);
        //set selected to finded position
        select.selectedIndex = optionIndex;
    });
}

function setFormInputDateToday() {
    Array.from(document.getElementsByClassName('newDatePicker')).forEach(
        inputDate => inputDate.valueAsDate = new Date()
    )
}

bindUnhideFormButtonsToParentHiddenForm(document.querySelectorAll(".unhideForm"));

function bindUnhideFormButtonsToParentHiddenForm(unhideButtonNodeList) {
    unhideButtonNodeList.forEach(
        function (unhideFormButton, currentIndex, listObj) {
            unhideFormButton.onclick = function () {
                unhideAll(unhideFormButton.parentElement);
                // remove .unhideForm button after click
                unhideFormButton.parentNode.removeChild(unhideFormButton);
            }
        });
}

function unhideAll(parentElement) {
    parentElement.childNodes.forEach(
        function (currentValue, currentIndex, listObj) {
            currentValue.hidden = false;
        });
}