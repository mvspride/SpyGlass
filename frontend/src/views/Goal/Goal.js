import React from "react";
import {Component} from "react";
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardFooter from "components/Card/CardFooter.js";
import propTypes from 'prop-types';
import Moment from 'react-moment';
// import { Container, Table } from 'reactstrap';
//import goalImage from "./download.jpg";
import './Goal.css';

class Goal extends Component{
  emptyItem = {
    id : 1,
    description: "",
    deadLine: new Date(),
    frequency: "weekly",
    priority: "high",
    contributionAmount: 0,
    goalAmount: 0,
    currentlySaved: 0,
    pictureURL: "",
    contributions: []
}  
  
  constructor(props){
        super(props);
        this.state ={
          item: this.emptyItem
        }
    }
   
    render(){
      return(
    <div>
      <GridContainer>
        <GridItem xs={12} sm={6} md={3}>
          
          <Card>
            <CardHeader color="warning" stats icon>
            <div className ="goalName">
                Goal Amount - ${this.props.goalAmount}
              </div>

            <div className = "images">
            <img src ={this.props.goalImage} width ="100%"/>
          </div>
              
              {/* <CardIcon color="warning">
                <Icon>content_copy</Icon>
              </CardIcon> */}
              <div className ="cardCategory">

                <div className= "goalPriority">
                  Priority : {this.props.priority}
                  <div className="goalFrequency">
                    Frequency : {this.props.frequency}
                    
                    </div>
                </div>
                
                <div className="goalDeadline">
                  DeadLine : <Moment date={this.props.deadLine} format="MM/DD/YYYY"></Moment>
                </div>
                <div className="goalContribution">
                  {this.props.frequency} Payments : ${this.props.contributionAmount}
                </div>
              </div>
            </CardHeader>
              <div className ="goalDescription">
                  Description : {this.props.description}
              </div>
            <CardFooter stats>
              <div style={{"textAlign":"center"}} className="stats">
                
                <a href="#pablo"  onClick={(e) => e.preventDefault()} >
                       edit Goal
                </a>
                
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        </GridContainer>
</div>
    );
  }
}

Goal.propTypes = {
  goalName: propTypes.string,
  goalAmount: propTypes.string,
  goalImage: propTypes.string,
  priority: propTypes.string,
  frequency: propTypes.string,
  deadLine: propTypes.string,
  contributionAmount: propTypes.string,
  description: propTypes.string
};

  
export default Goal;