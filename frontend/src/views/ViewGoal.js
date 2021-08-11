import React, { Component } from 'react';

class ViewGoal extends Component {
    // emptyItem = {

    //     description: "",
    //     deadLine: new Date(),
    //     frequency: "weekly",
    //     priority: "high",
    //     contributionAmount: 0,
    //     goalAmount: 0,
    //     currentlySaved: 0,
    //     pictureURL: "",
    //     contributions: []
    //  }
 
     
    //  constructor(props){
    //      super(props)
    //      this.state = {
    //          date: new Date(),
    //          isLoading : true,
    //          item: this.emptyItem
    //      }
    //      this.handleSubmit = this.handleSubmit.bind(this);
    //      this.handleChange = this.handleChange.bind(this);
    //      this.handleDateChange = this.handleDateChange.bind(this);
    //      this.handleFrequencyChange = this.handleFrequencyChange.bind(this);
    //      this.handlePriorityChange = this.handlePriorityChange.bind(this);
    //  }

    //  async SendGoal(id){
    //     event.preventDefault();
    //     const {item} = this.state;
    //     await fetch(`/goals/${id}`, {
    //         method: 'PUT',
    //         headers : {
    //             'Authorization': 'Bearer ' + localStorage.getItem('token'),
    //             'Accept' : 'application/json',
    //             'Content-Type' : 'application/json'
    //         },
    //         body : JSON.stringify(item)
    //     }).then(()=>window.location.reload(true));
    //     this.props.history.push("/goals")
    // }

    // handleChange(event){
    //     const target= event.target;
    //     const value=target.value;
    //     const name=target.name;
    //     let item={...this.state.item};
    //     item[name] = value;
    //     this.setState({item});
    // }

    // handleDateChange(date){
    //     let item ={...this.state.item};
    //     item.deadLine = date;
    //     this.setState({item});
    // }

    // handleFrequencyChange(event){
    //     const target= event.target;
    //     const value = target.value;
    //     const name=target.name;
    //     let item={...this.state.item};
    //     var frequency = value;
    //     item.frequency = frequency;
    //     this.setState({item});
    // }

    // handlePriorityChange(event){
    //     const target= event.target;
    //     const value = target.value;
    //     const name=target.name;
    //     let item={...this.state.item};
    //     var priority = value;
    //     item.priority = priority;
    //     this.setState({item});
    // }

    // render() { 
    //     const{state} = this.props.location

    //     let rows = state.contributions.map(contribution =>
    //         <tr key = {contribution.id}>
    //             <td>{contribution.amount}</td>
    //             <td><Moment date={contribution.contributionDate} format="MM/DD/YYYY"></Moment></td>
    //         </tr>)
    //     return ( 
    //         <Container>
    //             {title}
    //             <Form onSubmit={this.handleSubmit}>
    //                 <FormGroup>
    //                     <label for="description">Description</label>
    //                     <input type="text" name="description" id="title" 
    //                     onChange={this.handleChange} value={state.description}/>
    //                 </FormGroup>

    //                 <FormGroup>
    //                     <label for="frequency">Contribution Frequency</label>
    //                     <select onChange={this.handleFrequencyChange} value={state.frequency}> 
    //                         {frequencyList}
    //                     </select>
    //                 </FormGroup>

    //                 <FormGroup>
    //                     <label for="priority">Goal Priority</label>
    //                     <select onChange={this.handlePriorityChange} value={state.priority}>
    //                         {priorityList}
    //                     </select>
    //                 </FormGroup>

    //                 <FormGroup>
    //                     <label for="contributionAmount">Contribution Amount</label>
    //                     <input type="text" name="contributionAmount" id="title"
    //                     onChange={this.handleChange} value={state.contributionAmount}/>
    //                 </FormGroup>

    //                 <FormGroup>
    //                     <label for="currentlySaved">Amount Already Saved</label>
    //                     <input type="text" name="currentlySaved" id="title"
    //                     onChange={this.handleChange} value={state.currentlySaved}/>
    //                 </FormGroup>

    //                 <FormGroup>
    //                     <label for="deadLine">Goal Deadline</label>
    //                     <DatePicker selected={this.state.item.deadLine} onChange={this.handleDateChange} value={state.deadLine}/>
    //                 </FormGroup>

    //                 <FormGroup>
    //                     <Button color="primary" onClick={() => this.SendGoal(state.id)}>Save</Button>{' '}
    //                 </FormGroup>


    //             </Form>
    //         </Container>
    //      );
    // }
}
 

export default ViewGoal;