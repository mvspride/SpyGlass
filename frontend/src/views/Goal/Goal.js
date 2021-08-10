import React from "react";
import {Component} from "react";
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";
import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardFooter from "components/Card/CardFooter.js";
import goalImage from "/Users/studenteng01/Documents/codeDIFF/Project/SpyGlass(vanGuard)/frontend/src/assets/img/cover.jpeg";
import './Goal.css';

class Goal extends Component{
    constructor(props){
        super(props);
        this.state ={}
    }
   
    render(){
        return(
      <div>
        <GridContainer>
          <GridItem xs={12} sm={6} md={3}>
            
            <Card>
              <CardHeader color="warning" stats icon>
              <div className ="goalName">
                  {this.props.goalName} - {this.props.goalAmount}
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
                    DeadLine : {this.props.deadLine}
                  </div>
                  <div className="goalContribution">
                    {this.props.frequency} Payments : {this.props.contributionAmount}
                  </div>
                </div>
              </CardHeader>
                <div className ="goalDescription">
                    Discription : {this.props.description}
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

export default Goal;