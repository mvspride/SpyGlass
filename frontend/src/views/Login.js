//import { Redirect } from "react-router-dom";
import React, { Component } from 'react';
import {Form, FormGroup, Button} from 'reactstrap';

class Login extends Component {
    emptyUser = {
        username: "placeholder",
        email: "placeholder",
        password: "placeholder"
    }


    details = {
        username: "placeholder",
        password: "placeholder"
    }

    registrationResponse = ""

    constructor(props){
        super(props)
        this.state = {
            item: this.emptyUser,
            logindeets: this.details,
            isLoading: true,
            registrationResponse: "We've sent you an email with a confirmation link. Please click that link to confirm your email" 
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    async handleSubmit(event){
        console.log("submitted");
        event.preventDefault();
        const {item} = this.state;
        await fetch('http://localhost:8080/registration', {
            mode: 'cors',
            method: 'POST',
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(item)
        }).then(()=>{
            //window.location.reload(true);
            this.setState({isLoading:false});
            });
    }

    async login(){
        //event.preventDefault();
        //const {item} = this.state;
        const loginDetails = {username:"", password:""};
        //this.state.logindeets;
        loginDetails.username = this.state.item.email;
        loginDetails.password = this.state.item.password;
        console.log(loginDetails.username);
        const response = await fetch('http://localhost:8080/login/authorize', {
            mode: 'cors',
            method: 'POST',
            headers : {
                'Access-Control-Allow-Origin' : '*',
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(loginDetails)
        });
        const body = await response.json();
        console.log(body.accessToken);
        localStorage.setItem('token', body.accessToken);
    }

    handleChange(event){
        const target= event.target;
        const value=target.value;
        const name=target.name;
        let item={...this.state.item};
        item[name] = value;
        this.setState({item});
    }


    redirect = () => {
        window.location.href = 'http://localhost:8080/login';
        // maybe can add spinner while loading
        return null;
      }
      render() {
        const {isLoading} = this.state;
        var response = "";
        if(!isLoading)
            response = this.state.registrationResponse
        return (
            <Form onSubmit={this.handleSubmit}>
                <h3>Create a New Account</h3>

                <FormGroup>
                        <label htmlFor="email">Email Address</label>
                        <input type="text" name="email" id="title" 
                        onChange={this.handleChange} placeholder="Enter email"/>
                </FormGroup>

                <FormGroup>
                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" id="title" 
                        onChange={this.handleChange} placeholder="Enter username"/>
                </FormGroup>

                <FormGroup>
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" id="title" 
                        onChange={this.handleChange} placeholder="Enter password"/>
                </FormGroup>

                <div>{response}</div>

                <FormGroup>
                        <Button color="primary" type="submit">Register</Button>{' '}
                        <Button color="secondary" type="button" onClick={() => this.login()}>Login</Button>
                </FormGroup>
                <p className="forgot-password text-right">
                    Already have an account? Sign in <a href='http://localhost:8080/login'>here</a>
                </p>
            </Form>
        );
    }
}
 
export default Login;