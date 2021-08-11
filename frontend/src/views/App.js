import React, { Component } from 'react';
import {Route, BrowserRouter as Router, Switch} from 'react-router-dom';
import Goal from './Goal/Goal.js';
import EditGoal from './EditGoal.js';


class App extends Component {
    state = {  }
    render() { 
        return (
            <Router>
                <Switch>
                    <Route path='/goal' exact={true} component={Goal}/>
                    <Route path='/editGoal' exact={true} component={EditGoal}/>
                </Switch>
            </Router>
          );
    }
}
 
export default App;