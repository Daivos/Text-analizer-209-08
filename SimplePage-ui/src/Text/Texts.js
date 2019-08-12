import React, {Component} from 'react';
import axios from 'axios';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';
import IconButton from 'material-ui/IconButton';
import Info from 'material-ui/svg-icons/action/info';
import ShowWordsGroups from 'material-ui/svg-icons/image/slideshow';
import {blue500, greenA200} from 'material-ui/styles/colors';
import { Link } from 'react-router-dom';
import TextComponet from './TextComponet';
const styles = {
    marginLeft: 0,
    marginRight: 10
  
}

class Texts extends Component{

    constructor(props){
        super(props);
        this.state={
            fixedHeader: false,
            showRowHover: true,
            showCheckboxes: false,
            selectable: false,
            multiSelectable: false,
            enableSelectAll: false,
            deselectOnClickaway: true,
            height: '300px',
            showModal: false,

            allTexts:[],
            textId:'',
            textField:'',

            textComponetInfo:[],
           wordsGroupComponentInfo:[]
        }
    }

    //get all Words Groups
    componentWillMount(){
        axios.get("http://localhost:8081/api/texts")
        .then((response) => {       
            this.setState({allTexts: response.data});
        })
        .catch((error) => {
            console.log(error);
        }); 
    }
    

    closeModal=()=>{
        this.setState({showModal: false})
    }


   //get single text info
   getInfo=(textId)=>{
    axios.get("http://localhost:8081/api/singleText/"+textId)
    .then((response)=>{
        this.setState({textComponetInfo: response.data}); 
        this.setState({ showModal: !this.state.showModal });
    })
    .catch((error)=>{
        console.log(error);
    });  
}

    render(){
        var texts = this.state.allTexts.map((singleText, index) => (
            <TableRow key={index} >
                <TableRowColumn>{singleText.textField}</TableRowColumn>  
                <TableRowColumn>
                    <IconButton aria-label="Info" 
                        onClick={()=>this.getInfo(singleText.textId)}>
                            <Info                         
                         color={blue500}
                         hoverColor={greenA200}/>
                    </IconButton>
                </TableRowColumn>   
                <TableRowColumn>       
                     <IconButton containerElement=
                        {<Link to={"/wordsGroupsByTextId/"+singleText.textId} />}
                        linkButton={true}>
                        <ShowWordsGroups
                            color={blue500}
                            hoverColor={greenA200}/>
                     </IconButton> 
                </TableRowColumn>         
            </TableRow>
        ))    
         
        return(
            <MuiThemeProvider>
            <div>
            <h2>Texts</h2>
            <Table
                height={this.state.height}
                style={styles}
                fixedHeader={this.state.fixedHeader}
                selectable={this.state.selectable}
                multiSelectable={this.state.multiSelectable}
            >
                <TableHeader
                displaySelectAll={this.state.showCheckboxes}
                adjustForCheckbox={this.state.showCheckboxes}
                enableSelectAll={this.state.enableSelectAll}
                >
                <TableRow>
                    <TableHeaderColumn
                        className="text"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word",
                            width: "60%"
                        }}
                        tooltip="text">Text</TableHeaderColumn> 
                    <TableHeaderColumn
                        className="icons"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word"
                        }}
                        tooltip="Full text">Full text</TableHeaderColumn>    
                    <TableHeaderColumn
                        className="icons"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word"
                        }}
                        tooltip="Words by last letter">Words by last letter</TableHeaderColumn>    
                </TableRow>
                </TableHeader>

                <TableBody
                    displayRowCheckbox={this.state.showCheckboxes}
                    deselectOnClickaway={this.state.deselectOnClickaway}
                    showRowHover={this.state.showRowHover}>
                    {texts}
                </TableBody>
            </Table>
            <TextComponet
                open={this.state.showModal}
                closeAction={this.closeModal}
                textComponetInfo={this.state.textComponetInfo}  
                textId={this.state.textId}
                />
            </div>
        </MuiThemeProvider>
        );
    }
};
export default Texts;
    
