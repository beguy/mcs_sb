function synchronizeSelectId() {
    var selects = document.getElementsByClassName("selectWithSynchronizedId");
    selects = Array.from(selects); //convert to array

    selects.forEach(select=>{
        //get id from classname: selected=#id
        let classNames = Array.from(select.classList);
        let selectedClassNamePosition = classNames.findIndex(className=>className.includes("selected"));
        let id = classNames[selectedClassNamePosition].split("=")[1];
        //find option position from value
        let options = Array.from(select.options);
        let optionIndex = options.map(option=>option.value)
            .findIndex(value=>(value == id) ? true : false);
        //set selected to finded position
        select.selectedIndex = optionIndex;
    });
}

function bindUnhideFormButtonsToParentHiddenForm(unhideButtonNodeList) {
    unhideButtonNodeList.forEach(
        function (unhideFormButton, currentIndex, listObj) {
            let originalFn = unhideFormButton.onclick;
            unhideFormButton.onclick = function () {
                unhideAndEnableAll(unhideFormButton.parentElement);
                // remove .unhideForm button after click
                unhideFormButton.parentNode.removeChild(unhideFormButton);
                if (originalFn != null)
                    return originalFn.apply(this, arguments);
            }
        });
}

function unhideAndEnableAll(parentElement) {
    parentElement.childNodes.forEach(
        function (currentValue, currentIndex, listObj) {
            currentValue.hidden = false;
            currentValue.disabled = false;
        });
}