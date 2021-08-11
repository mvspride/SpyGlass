import React, { Component } from 'react';
import DatePicker from 'react-datepicker';
//import './App.css';
import "react-datepicker/dist/react-datepicker.css";
import {Container, Form, FormGroup, Button} from 'reactstrap';
//import {Link} from 'react-router-dom';
//import Moment from 'react-moment';
// import GridItem from "components/Grid/GridItem.js";
// import GridContainer from "components/Grid/GridContainer.js";
// import Card from "components/Card/Card.js";
// import CardHeader from "components/Card/CardHeader.js";
// import CardFooter from "components/Card/CardFooter.js";
import Sidebar from 'components/Sidebar/Sidebar.js';
import routes from "routes.js";
import logo from "assets/img/reactlogo.png";
import bgImage from "assets/img/sidebar-2.jpg";
import 'react-router-dom';
import propTypes from "prop-types";
// import styles from "assets/jss/material-dashboard-react/layouts/adminStyle.js"
// import { makeStyles } from '@material-ui/core';

class AddGoal extends Component {

    emptyItem = {

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
        super(props)
        this.state = {
            date: new Date(),
            item: this.emptyItem,
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleFrequencyChange = this.handleFrequencyChange.bind(this);
        this.handlePriorityChange = this.handlePriorityChange.bind(this);
        this.handleDrawerToggle = this.handleDrawerToggle.bind(this);
    }


    async handleSubmit(event){
        event.preventDefault();
        const {item} = this.state;
        await fetch('/goals', {
            mode: 'cors',
            method: 'POST',
            headers : {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(item)
        }).then(()=>this.props.history.push("/admin/dashboard"));
    }

    handleChange(event){
        const target= event.target;
        const value=target.value;
        const name=target.name;
        let item={...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    handleDateChange(date){
        let item ={...this.state.item};
        item.deadLine = date;
        this.setState({item});
    }

    handleFrequencyChange(event){
        const target= event.target;
        const value = target.value;
        //const name=target.name;
        let item={...this.state.item};
        var frequency = value;
        item.frequency = frequency;
        this.setState({item});
    }

    handlePriorityChange(event){
        const target= event.target;
        const value = target.value;
        //const name=target.name;
        let item={...this.state.item};
        var priority = value;
        item.priority = priority;
        this.setState({item});
    }
    

    handleDrawerToggle(){
        
    }



    render( {...rest} ) { 

        
        // const content = {
        //     height: 5000,
        //     width: 5000,
        // }
        const title= <h3>Add Goal</h3>
        //const {goals} = this.state;

        let frequency = [
            {id:1, name:"daily"},
            {id:2, name:"weekly"},
            {id:3, name:"bi-weekly"},
            {id:4, name:"monthly"},
            {id:5, name:"bi-monthly"}
        ]

        let priority = [
            {id:1, name:"low"},
            {id:2, name:"medium"},
            {id:3, name:"high"}
        ]

        let frequencyList = 
            frequency.map( freq =>
                <option id={freq.id} key={freq.id}>
                    {freq.name}
                </option>)
        
        let priorityList = 
            priority.map( pri =>
                <option id={pri.id} key={pri.id}>
                    {pri.name}
                </option>)

        return ( 
            <div style={{"textAlign":"center"}}>
            

            
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <label htmlFor="description">Description</label>
                        <input type="text" name="description" id="title" 
                        onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="goalAmount">Goal Amount</label>
                        <input type="text" name="goalAmount" id="title"
                        onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="frequency">Contribution Frequency</label>
                        <select onChange={this.handleFrequencyChange}>
                            {frequencyList}
                        </select>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="priority">Goal Priority</label>
                        <select onChange={this.handlePriorityChange}>
                            {priorityList}
                        </select>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="contributionAmount">Contribution Amount</label>
                        <input type="text" name="contributionAmount" id="title"
                        onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="currentlySaved">Amount Already Saved</label>
                        <input type="text" name="currentlySaved" id="title"
                        onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="deadLine">Goal Deadline</label>
                        <DatePicker selected={this.state.item.deadLine} onChange={this.handleDateChange}/>
                    </FormGroup>

                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                    </FormGroup>


                </Form>
            </Container>
            <Sidebar
                routes={routes}
                logoText={"SpyGlass"}
                logo={logo}
                image={bgImage}
                handleDrawerToggle={this.handleDrawerToggle}
                open={false}  
                color={"blue"}
                {...rest}
            />
            </div>
            
            );
    }
}

AddGoal.propTypes = {
    history: propTypes.node
}
 
export default AddGoal;