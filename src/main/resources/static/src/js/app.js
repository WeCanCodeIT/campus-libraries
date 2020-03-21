import {
    renderHeader
} from './components/HeaderComponent.js'

import {
    renderCampuses
} from './components/CampusesComponent.js'

import {
    renderFooter
} from './components/FooterComponent.js'

const renderHomeView = () => {
    const anchor = document.querySelector('.anchor');
    anchor.appendChild(renderHeader());
    anchor.appendChild(document.createElement('main'));
    renderCampuses();
    anchor.appendChild(renderFooter());
}

renderHomeView();