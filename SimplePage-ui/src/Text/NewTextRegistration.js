import React, {Component} from 'react';
import axios from 'axios';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import swal from 'sweetalert';

class NewTextRegistration extends Component{
    constructor(props){
        super(props);
        this.state = {
            textField:'',
        };
    }

    submit = (event)=> {
        let textRegistrationInfo = {
            textField: this.state.textField,
        }
        axios.post('http://localhost:8081/api/newText', textRegistrationInfo)
        .then((response)=>{
            swal({
                text: "Text accepted",
                icon: "success",
               button: "OK",
            });
            this.refs.form.reset();
        })
        .catch ((error)=>{
            console.log(error);
            swal({
                text: "Text registration failed!",
                icon: "error",
               button: "OK",
            });
        })
        console.log(this.state);
        event.preventDefault();
    }

    render(){
        return(
            <MuiThemeProvider>
                <div >
                <form className="enterText"
                ref="form"
                open={this.props.open}> 
                    <h2> Enter the text </h2>
                    <TextField
                        fullWidth
                        rows="3"
                        style={{ margin: 8 }}
                        className="text"
                        floatingLabelText="Enter the text"
                        floatingLabelFixed={true}
                        onChange={(event, newValue) => this.setState({ textField: newValue })}/>
                    <br />
                  
                    <br />
                    <RaisedButton 
                        label="Submit" 
                        primary={true}  
                        onClick={(event)=>this.submit(event)} 
                    />
                </form>
                </div>
            </MuiThemeProvider>
        )
    }

}export default NewTextRegistration;