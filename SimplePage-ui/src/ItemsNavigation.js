import { Route, Switch } from 'react-router-dom';
import React, { Component } from 'react';
import Texts from './Text/Texts';
import NewTextRegistration from './Text/NewTextRegistration';
import WordsGroupTable from './WordsGroups/WordsGroupTable';
import WordsGroupsByText from './WordsGroups/WordsGroupsByText';
import ImportFromFileBodyComponent from './Text/ImportFromFileBodyComponent';

class ItemsNavigation extends Component{
    render(){
        return(
            <main>
                <Switch>
                    <Route path="/newText" component={NewTextRegistration}/>  
                     <Route path="/newText" component={ImportFromFileBodyComponent}/> 
                    <Route path="/wordsGroups" component={WordsGroupTable}/>
                    <Route path="/texts" component={Texts}/>
                    <Route path="/wordsGroupsByTextId/:textId" component={WordsGroupsByText}/>
                </Switch>    
            </main>
        )
    }
}export default ItemsNavigation;