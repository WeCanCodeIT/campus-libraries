import {
    DomMaker
} from "./DomMaker.js"

class InputMaker extends DomMaker {
    constructor() {
        super('input');
    }
    setInputType(inputType) {
        this.htmlElement.setAttribute('type', inputType);
        return this;
    }
    setPlaceholder(placeholder) {
        this.htmlElement.setAttribute('placeholder', placeholder);
        return this;
    }
}

export {
    InputMaker,
    DomMaker
}