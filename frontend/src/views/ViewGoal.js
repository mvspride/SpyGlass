import React, { Component } from 'react';
import {Container, Form, FormGroup, Button} from 'reactstrap';
import DatePicker from 'react-datepicker';
import Moment from 'react-moment';
import propTypes from 'prop-types';
import 'react-router-dom';

class ViewGoal extends Component {
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
             isLoading : true,
             item: this.emptyItem
         }
         //this.handleSubmit = this.handleSubmit.bind(this);
         this.SendGoal = this.SendGoal.bind(this);
         this.handleChange = this.handleChange.bind(this);
         this.handleDateChange = this.handleDateChange.bind(this);
         this.handleFrequencyChange = this.handleFrequencyChange.bind(this);
         this.handlePriorityChange = this.handlePriorityChange.bind(this);
     }

     async SendGoal(id){
        //event.preventDefault();
        const {item} = this.state;
        await fetch(`/goals/${id}`, {
            method: 'PUT',
            headers : {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(item)
        }).then(()=>window.location.reload(true));
        //this.props.history.push("/admin/dashboard")
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

    render() { 
        const{state} = this.props.goal


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

        let rows = state.contributions.map(contribution =>
            <tr key = {contribution.id}>
                <td>{contribution.amount}</td>
                <td><Moment date={contribution.contributionDate} format="MM/DD/YYYY"></Moment></td>
            </tr>)
        return ( 
            <div>
            <Container>

                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <label htmlFor="description">Description</label>
                        <input type="text" name="description" id="title" 
                        onChange={this.handleChange} value={state.description}/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="frequency">Contribution Frequency</label>
                        <select onChange={this.handleFrequencyChange} value={state.frequency}> 
                            {frequencyList}
                        </select>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="priority">Goal Priority</label>
                        <select onChange={this.handlePriorityChange} value={state.priority}>
                            {priorityList}
                        </select>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="contributionAmount">Contribution Amount</label>
                        <input type="text" name="contributionAmount" id="title"
                        onChange={this.handleChange} value={state.contributionAmount}/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="currentlySaved">Amount Already Saved</label>
                        <input type="text" name="currentlySaved" id="title"
                        onChange={this.handleChange} value={state.currentlySaved}/>
                    </FormGroup>

                    <FormGroup>
                        <label htmlFor="deadLine">Goal Deadline</label>
                        <DatePicker selected={this.state.item.deadLine} onChange={this.handleDateChange} value={state.deadLine}/>
                    </FormGroup>

                    <FormGroup>
                        <Button color="primary" onClick={() => this.SendGoal(state.id)}>Save</Button>{' '}
                    </FormGroup>


                </Form>
            </Container>
            <Container>
                {rows}
            </Container>
            </div>
         );
    }
}
 
ViewGoal.propTypes = {
    goal: propTypes.node,
    history: propTypes.node
}

export default ViewGoal;