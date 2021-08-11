import React,{Component} from "react";
// react plugin for creating charts
// import ChartistGraph from "react-chartist";
// @material-ui/core
// import { makeStyles } from "@material-ui/core/styles";
// import Icon from "@material-ui/core/Icon";
// @material-ui/icons
// import Store from "@material-ui/icons/Store";
// import Warning from "@material-ui/icons/Warning";
// import DateRange from "@material-ui/icons/DateRange";
// import LocalOffer from "@material-ui/icons/LocalOffer";
// import Update from "@material-ui/icons/Update";
// import ArrowUpward from "@material-ui/icons/ArrowUpward";
// import AccessTime from "@material-ui/icons/AccessTime";
// import Accessibility from "@material-ui/icons/Accessibility";
// import BugReport from "@material-ui/icons/BugReport";
// import Code from "@material-ui/icons/Code";
// import Cloud from "@material-ui/icons/Cloud";
// core components
// import GridItem from "components/Grid/GridItem.js";
// import GridContainer from "components/Grid/GridContainer.js";
// import Table from "components/Table/Table.js";
// import Tasks from "components/Tasks/Tasks.js";
// import CustomTabs from "components/CustomTabs/CustomTabs.js";
// import Danger from "components/Typography/Danger.js";
// import Card from "components/Card/Card.js";
// import CardHeader from "components/Card/CardHeader.js";
// import CardIcon from "components/Card/CardIcon.js";
// import CardBody from "components/Card/CardBody.js";
// import CardFooter from "components/Card/CardFooter.js";
// import goalImage from "./download.jpg";
// import { bugs, website, server } from "variables/general.js";
import './Dashboard.css';
import Goal from '../Goal/Goal';
//import uploadIcon from '../../uploadIcon.png';
import {Link} from 'react-router-dom';
import {Button} from 'reactstrap';
//import Moment from 'react-moment';

// import {
//   dailySalesChart,
//   emailsSubscriptionChart,
//   completedTasksChart,
// } from "variables/charts.js";

// // import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
// import { FormatAlignLeft } from "@material-ui/icons";
// import { red } from "@material-ui/core/colors";
// //import Dashboard from "@material-ui/icons/Dashboard";


// let styles= {cardTitle:{color : "black", textAlign : "left"},
// cardBody: {color : "black",textAlign : "left"},
// cardFrequency :{color : "black" ,textAlign : "right",marginTop : "40px"
// }}

// const useStyles = makeStyles(styles);

class Dashboard extends Component{
  constructor(props){
      super(props);
      this.state ={
        isLoading: true,
        goalArray:[]
      }
  }

  async componentDidMount(){
    console.log("mounted");
    const response= await fetch('http://localhost:8080/goals', 
    {
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('token')},
      mode: 'cors',
      method: 'GET'
    });
    const body = await response.json();
    this.setState({goalArray:body, isLoading:false});
  }
 
//   getGoal =()=>{
//     let data =[
//       {
//       "goalName" : "Going to Italy", 
//       "goalImage" : "https://media.worldnomads.com/Explore/europe/5-things-italy.jpg",
//       "priority" :"High",
//       "frequency" : "Monthly", 
//       "goalAmount" : "$2000",
//       "contributionAmount" : "$400",
//       "deadLine" : "soon my guy",
//       "description" : "Yessir we out"
//       }
//     ];
//     this.setState({goalArray: data});
// }
  render(){

    const {goalArray, isLoading} = this.state;

    if(isLoading)
      return(<div>Loading your goals! This should just take a second.</div>)

    let rows = 
            goalArray.map( goal =>
              // <tr key={goal.id}>
              // <td>
                <Goal 
                id={goal.id} 
                key={goal.id} 
                //goalName = {goal.goalName} 
                goalImage = {goal.pictureURL} 
                priority = {goal.priority}
                frequency = {goal.frequency} 
                goalAmount = {goal.goalAmount} 
                contributionAmount = {goal.contributionAmount} 
                currentlySaved = {goal.currentlySaved}
                deadLine = {goal.deadLine}  
                description = {goal.description}
                contributions = {goal.contributions}
                />
                // </td>
                // <td>
                // <Link to={{ pathname: "/goal", state: goal}}>
                //     <Button size="sm" color="primary">View</Button>
                // </Link>
                // </td>
                // </tr>
                        
              // <tr key={goal.id}>
              //       <td>{goal.description}</td>
              //       <td><Moment date={goal.date} format="MM/DD/YYYY"></Moment></td>
              //       <td>{goal.goalAmount}</td>
              //       <td>{goal.currentlySaved}</td>
              //       {/* <td>
              //           <Button size="sm" color="danger" onClick={ () => this.remove(goal.id)}>Delete</Button>
              //           <Link to={{ pathname: "/goal", state: goal}}>
              //               <Button size="sm" color="primary">View</Button>
              //           </Link>
              //       </td> */}
                    
              //   </tr>
              )
                
                
                
                
      return(
    <div>
      <div style={{"textAlign":"center"}}>
        <Link to={{pathname: "/addGoal"}}>
          <Button size="sm" color="secondary">Add a New Goal</Button>
        </Link>
      </div>
      {
        
        rows
        // this.state.goalArray.map((item)=>
        //   <Goal id={item.goalId} key={item.goalId} goalName = {item.goalName} goalImage = {item.goalImage} priority = {item.priority}
        //         frequency = {item.frequency} goalAmount = {item.goalAmount} contributionAmount = {item.contributionAmount} 
        //         deadLine = {item.deadLine} description = {item.description}/>)
      }
    </div>


      );
    }
  }


export default Dashboard;

// export default function Dashboard() {
//   const classes = useStyles();
//   return (
//     <div>
//       <GridContainer>
//         <GridItem xs={12} sm={6} md={3}>
//           <div className = "Dashboard_t">
//             <img src ={goalImage} width ="600px"/>
//           </div>
//           <Card>
//             <CardHeader color="warning" stats icon>
//               <h3 className ={classes.cardTitle}>
//                 <small>Goal Name</small>
//               </h3>
//             <img src ={goalImage} width ="600px" />

//               {/* <CardIcon color="warning">
//                 <Icon>content_copy</Icon>
//               </CardIcon> */}
//               <h3 className= {classes.cardTitle}>
//               <small>Priority : High </small>
//               </h3>
//               <h3 className={classes.cardFrequency}>
//                 <small>Frequency : Weekly</small>
//                 </h3>
//               <h3 className={classes.cardTitle}>
//                 49/50 <small>GB</small>
//               </h3>
//             </CardHeader>
//             <CardFooter stats>
//               <div className={classes.stats}>
//                 <Danger>
//                   <Warning />
//                 </Danger>
//                 <a href="#pablo" onClick={(e) => e.preventDefault()}>
//                   Get more space
//                 </a>
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem>
//         {/* <GridItem xs={12} sm={6} md={3}>
//           <Card>
//             <CardHeader color="success" stats icon>
//               <CardIcon color="success">
//                 <Store />
//               </CardIcon>
//               <p className={classes.cardCategory}>Revenue</p>
//               <h3 className={classes.cardTitle}>$34,245</h3>
//             </CardHeader>
//             <CardFooter stats>
//               <div className={classes.stats}>
//                 <DateRange />
//                 Last 24 Hours
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem>
//         <GridItem xs={12} sm={6} md={3}>
//           <Card>
//             <CardHeader color="danger" stats icon>
//               <CardIcon color="danger">
//                 <Icon>info_outline</Icon>
//               </CardIcon>
//               <p className={classes.cardCategory}>Fixed Issues</p>
//               <h3 className={classes.cardTitle}>75</h3>
//             </CardHeader>
//             <CardFooter stats>
//               <div className={classes.stats}>
//                 <LocalOffer />
//                 Tracked from Github
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem>
//         <GridItem xs={12} sm={6} md={3}>
//           <Card>
//             <CardHeader color="info" stats icon>
//               <CardIcon color="info">
//                 <Accessibility />
//               </CardIcon>
//               <p className={classes.cardCategory}>Followers</p>
//               <h3 className={classes.cardTitle}>+245</h3>
//             </CardHeader>
//             <CardFooter stats>
//               <div className={classes.stats}>
//                 <Update />
//                 Just Updated
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem> */}
//       </GridContainer>
//       {/* <GridContainer>
//         <GridItem xs={12} sm={12} md={4}>
//           <Card chart>
//             <CardHeader color="success">
//               <ChartistGraph
//                 className="ct-chart"
//                 data={dailySalesChart.data}
//                 type="Line"
//                 options={dailySalesChart.options}
//                 listener={dailySalesChart.animation}
//               />
//             </CardHeader>
//             <CardBody>
//               <h4 className={classes.cardTitle}>Daily Sales</h4>
//               <p className={classes.cardCategory}>
//                 <span className={classes.successText}>
//                   <ArrowUpward className={classes.upArrowCardCategory} /> 55%
//                 </span>{" "}
//                 increase in today sales.
//               </p>
//             </CardBody>
//             <CardFooter chart>
//               <div className={classes.stats}>
//                 <AccessTime /> updated 4 minutes ago
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem>
//         <GridItem xs={12} sm={12} md={4}>
//           <Card chart>
//             <CardHeader color="warning">
//               <ChartistGraph
//                 className="ct-chart"
//                 data={emailsSubscriptionChart.data}
//                 type="Bar"
//                 options={emailsSubscriptionChart.options}
//                 responsiveOptions={emailsSubscriptionChart.responsiveOptions}
//                 listener={emailsSubscriptionChart.animation}
//               />
//             </CardHeader>
//             <CardBody>
//               <h4 className={classes.cardTitle}>Email Subscriptions</h4>
//               <p className={classes.cardCategory}>Last Campaign Performance</p>
//             </CardBody>
//             <CardFooter chart>
//               <div className={classes.stats}>
//                 <AccessTime /> campaign sent 2 days ago
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem>
//         <GridItem xs={12} sm={12} md={4}>
//           <Card chart>
//             <CardHeader color="danger">
//               <ChartistGraph
//                 className="ct-chart"
//                 data={completedTasksChart.data}
//                 type="Line"
//                 options={completedTasksChart.options}
//                 listener={completedTasksChart.animation}
//               />
//             </CardHeader>
//             <CardBody>
//               <h4 className={classes.cardTitle}>Completed Tasks</h4>
//               <p className={classes.cardCategory}>Last Campaign Performance</p>
//             </CardBody>
//             <CardFooter chart>
//               <div className={classes.stats}>
//                 <AccessTime /> campaign sent 2 days ago
//               </div>
//             </CardFooter>
//           </Card>
//         </GridItem>
//       </GridContainer> */}
//       {/* <GridContainer>
//         <GridItem xs={12} sm={12} md={6}>
//           <CustomTabs
//             title="Tasks:"
//             headerColor="primary"
//             tabs={[
//               {
//                 tabName: "Bugs",
//                 tabIcon: BugReport,
//                 tabContent: (
//                   <Tasks
//                     checkedIndexes={[0, 3]}
//                     tasksIndexes={[0, 1, 2, 3]}
//                     tasks={bugs}
//                   />
//                 ),
//               },
//               {
//                 tabName: "Website",
//                 tabIcon: Code,
//                 tabContent: (
//                   <Tasks
//                     checkedIndexes={[0]}
//                     tasksIndexes={[0, 1]}
//                     tasks={website}
//                   />
//                 ),
//               },
//               {
//                 tabName: "Server",
//                 tabIcon: Cloud,
//                 tabContent: (
//                   <Tasks
//                     checkedIndexes={[1]}
//                     tasksIndexes={[0, 1, 2]}
//                     tasks={server}
//                   />
//                 ),
//               },
//             ]}
//           />
//         </GridItem>
//         <GridItem xs={12} sm={12} md={6}>
//           <Card>
//             <CardHeader color="warning">
//               <h4 className={classes.cardTitleWhite}>Employees Stats</h4>
//               <p className={classes.cardCategoryWhite}>
//                 New employees on 15th September, 2016
//               </p>
//             </CardHeader>
//             <CardBody>
//               <Table
//                 tableHeaderColor="warning"
//                 tableHead={["ID", "Name", "Salary", "Country"]}
//                 tableData={[
//                   ["1", "Dakota Rice", "$36,738", "Niger"],
//                   ["2", "Minerva Hooper", "$23,789", "Curaçao"],
//                   ["3", "Sage Rodriguez", "$56,142", "Netherlands"],
//                   ["4", "Philip Chaney", "$38,735", "Korea, South"],
//                 ]}
//               />
//             </CardBody>
//           </Card>
//         </GridItem>
//       </GridContainer> */}
//     </div>
//   );
// }