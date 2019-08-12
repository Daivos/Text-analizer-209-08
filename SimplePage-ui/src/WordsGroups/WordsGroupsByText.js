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
 import {blue500, greenA200} from 'material-ui/styles/colors';
 import WordsGroupComponet from './WordsGroupComponet';


const styles = {
    marginLeft: 0,
    marginRight: 10
  
}

class WordsGroupsByText extends Component{

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

           
            wordsGroupId:'',
            letter:'',
            wordsAmount: '',
            words:'',
            text:'',
            textField:'',
            textInfo:'',

            allWordsGroupsByTextId:[],
           

            wordsGroupComponentInfo:[]
        }
    }

   //get all Words Groups
   componentWillMount(){
    let textId = this.props.match.params.textId
    axios.get("http://localhost:8081/api/wordsGroupsByTextId/"+textId)
    .then((response) => {       
        this.setState({allWordsGroupsByTextId: response.data});
    })
    .catch((error) => {
        console.log(error);
    }); 
}


//get Text info    
    getTextInfo=(wordsGroupId)=>{
    axios.get("http://localhost:8081/api/singleWordsGroup/"+wordsGroupId)
        .then((response)=>{
        this.setState({wordsGroupComponetInfo: response.data});
        this.setState({ showModal: !this.state.showModal });
    })
    .catch((error) => {
        console.log(error);
    }); 
}


closeModal=()=>{
    this.setState({showModal: false})
}

render(){
   
    var wordsGroups = this.state.allWordsGroupsByTextId.map((singleWordsGroup, index) => (
        <TableRow key={index} >
            <TableRowColumn>{singleWordsGroup.letter}</TableRowColumn>
            <TableRowColumn>{singleWordsGroup.wordsAmount}</TableRowColumn>
            <TableRowColumn>{singleWordsGroup.words}</TableRowColumn>  
            <TableRowColumn>
                    <IconButton aria-label="Info" 
                        onClick={()=>this.getTextInfo(singleWordsGroup.wordsGroupId)}>
                            <Info                         
                         color={blue500}
                         hoverColor={greenA200}/>
                    </IconButton>
                </TableRowColumn> 
        </TableRow>
    )) 
  

    
         
        return(
            
            <MuiThemeProvider>
            <div>
            <h2>Words by last letter</h2>
            
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
                 {this.state.textComponetInfo}
                <TableRow>
                    <TableHeaderColumn
                        className="letter"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word",
                            width: "10%"
                        }}
                        tooltip="wordsGroupLetter">Letter</TableHeaderColumn>
                    <TableHeaderColumn
                        className="wordsAmount"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word",
                            width: "10%"
                        }}
                        tooltip="wordsAmount">Words Amount</TableHeaderColumn>
                     <TableHeaderColumn
                        className="words"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word",
                            width: "70%"
                        }}
                        tooltip="words">Words</TableHeaderColumn>
                       <TableHeaderColumn
                        className="icons"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word"
                        }}
                        tooltip="Full text">Full text</TableHeaderColumn>    
                </TableRow>
                </TableHeader>

                <TableBody
                    displayRowCheckbox={this.state.showCheckboxes}
                    deselectOnClickaway={this.state.deselectOnClickaway}
                    showRowHover={this.state.showRowHover}>
                    {wordsGroups}
                </TableBody>
            </Table>
            <WordsGroupComponet
                open={this.state.showModal}
                closeAction={this.closeModal}
                wordsGroupComponetInfo={this.state.wordsGroupComponetInfo} 
                wordsGroupId={this.state.wordsGroupId}
                />
            </div>
        </MuiThemeProvider>
        );
    }
};
export default WordsGroupsByText;
    
