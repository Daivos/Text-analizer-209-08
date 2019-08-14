import React, {Component} from 'react';
import axios from 'axios';
//import swal from 'sweetalert';

class ImportFromFileBodyComponent extends Component{

    constructor(props) {
        super(props);
        this.state ={
          file:null
        }
        
        this.onFormSubmit = this.onFormSubmit.bind(this)
        this.onChange = this.onChange.bind(this)
        this.fileUpload = this.fileUpload.bind(this)

      }
      onFormSubmit(e){
        e.preventDefault() // Stop form submit
        let fileRegistration = this.fileUpload(this.state.file)

        axios.post('http://localhost:8081/api/newText', fileRegistration)
        .then(res => { // then print response status
          console.log(res.statusText)
       })
      }
        
      //   .then((response)=>{
      //       swal({
      //           text: "Text accepted",
      //           icon: "success",
      //          button: "OK",
      //       });
      //       this.refs.form.reset();
      //   })
      //   .catch ((error)=>{
      //       console.log(error);
      //       swal({
      //           text: "Text registration failed!",
      //           icon: "error",
      //          button: "OK",
      //       });
      //   })
      //   console.log(this.state);
        
      // }

      onChange(e) {
        this.setState({file:e.target.files[0]})
      }

      fileUpload(file){
        const url = 'http://localhost:8081/api/newText';
        const formData = new FormData();
        formData.append('file',this.state.file)
        
        const config = {
            headers: {
                'content-type': 'multipart/form-data',
                
            }
        }
        return  axios.post(url, formData, config)
      }
    
      render() {
        return (
          <form onSubmit={this.onFormSubmit}>
            <h1>File Upload</h1>
            <input type="file" onChange={this.onChange} />
            <button type="submit">Upload</button>
          </form>
       )
      }
    }
    
    export default ImportFromFileBodyComponent;
        
