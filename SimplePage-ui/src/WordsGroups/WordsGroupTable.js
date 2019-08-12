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
import WordsGroupComponet from './WordsGroupComponet';


const styles = {
    marginLeft: 0,
    marginRight: 10
  
}

class WordsGroupTable extends Component{

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

            allWordsGroups:[],
            wordsGroupId:'',
            letter:'',
            wordsAmount: '',
            words:'',
            text:'',

           wordsGroupComponentInfo:[]
        }
    }

    //get all Words Groups
    componentWillMount(){
        axios.get("http://localhost:8081/api/wordsGroups")
        .then((response) => {       
            this.setState({allWordsGroups: response.data});
        })
        .catch((error) => {
            console.log(error);
        }); 
    }

   closeModal=()=>{
        this.setState({showModal: false})
    }

    render(){
        var wordsGroups = this.state.allWordsGroups.map((singleWordsGroup, index) => (
            <TableRow key={index} >
                <TableRowColumn>{singleWordsGroup.letter}</TableRowColumn>
                <TableRowColumn>{singleWordsGroup.wordsAmount}</TableRowColumn>
                <TableRowColumn>{singleWordsGroup.words}</TableRowColumn>  
                <TableRowColumn>{singleWordsGroup.fullText}</TableRowColumn>  
            </TableRow>
        ))    
         
        return(
            <MuiThemeProvider>
            <div>
            <h2>Words group hystory table</h2>
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
                            width: "40%"
                        }}
                        tooltip="words">Words</TableHeaderColumn>
                    <TableHeaderColumn
                        className="text"
                        style={{
                            whiteSpace: "normal",
                            wordWrap: "break-word",
                            width: "40%"
                        }}
                        tooltip="text">Text</TableHeaderColumn> 
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
                />
            </div>
        </MuiThemeProvider>
        );
    }
};
export default WordsGroupTable;
    
