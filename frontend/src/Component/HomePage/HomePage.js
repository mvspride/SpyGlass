import React, {Component} from 'react';
import Grid from '@material-ui/core/Grid';
import Goal from '../Goal/Goal.js';

class HomePage extends Component{
    constructor(props){
    super(props);
    this.state ={}
    }
    render(){
        return(
            <div>
                <Grid container>
                    <Grid item xs={3}>
                        1st
                    </Grid>
                    <Grid item xs ={6}>
                        <Goal/>
                    </Grid>
                    <Grid item xs ={3}>
                        3rd
                    </Grid>
                </Grid>
            </div>
        );
    }
}
    
  export default HomePage;
