class DomMaker {

    constructor(elementType) {
        this.htmlElement = document.createElement(elementType);
    }

    addClassName(className) {
        this.htmlElement.classList.add(className);
        return this;
    }
    appendChild(element) {
        this.htmlElement.append(element);
        return this;
    }

    changeContent(newContent) {
        this.htmlElement.innerText = newContent;
        return this;
    }

    addOnClickBehavior(functionToRun) {
        this.htmlElement.addEventListener('click', functionToRun);
        return this;
    }

    render() {
        return this.htmlElement;
    }
}

export {
    DomMaker
};