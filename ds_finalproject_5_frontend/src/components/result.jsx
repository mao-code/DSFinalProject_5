import { View, Text, StyleSheet, Linking, SafeAreaView, TouchableOpacity, ScrollView, Button } from 'react-native'
import React, { useState } from 'react';
import Icon from 'react-native-vector-icons/AntDesign';
import SearchService from '../services/searchService';
import LoadingComponent from './loading';

export default function ResultComponent({keyword, searchRes, onBackToSearch}) {
  const [input, setInput] = useState(searchRes);
  const [isLoading, setIsLoading] = useState(false);
  const [myResult, setMyResult] = useState(input["myResult"]);
  const [history, setHistory] = useState(myResult); // store historical results
  const [indexLength, setIndexLength] = useState([{index: 0, length: myResult.length}]);
  const [currentIndex, setCurrentIndex] = useState(0);
  // const [originalGoogleResult, setOriginalGoogleResult] = useState(input["orginalGoogleResult"]);

  // demo use
  console.log(myResult.map(_=> { return {"name": _.displayName, "score": _.score} }));
  // console.log(originalGoogleResult);
  // console.log(`Now index: ${currentIndex}, Length: ${indexLength.filter(_=>_.index==currentIndex)[0].length}`);
  // console.log(history);

  const handlePagination = async (type) => {
    switch(type)
    {
      case 'pre':
        if(currentIndex==0){
          return;
        }
        let tempIndex = currentIndex-1;
        setCurrentIndex(currentIndex-1);

        let currentLength = indexLength.filter(_=>_.index==tempIndex)[0].length;
        let accumulatedLength = indexLength.filter(_=>_.index<tempIndex).map(_=>_.length).reduce((sum, l)=>sum+l, 0);
        setMyResult(history.slice(accumulatedLength, accumulatedLength+currentLength));
        break;
      case 'next':
        //if searched, don't need to search again
        if(indexLength.filter(_=>_.index==currentIndex+1).length>0){
          let tempIndex = currentIndex+1;
          setCurrentIndex(currentIndex+1);
          let currentLength = indexLength.filter(_=>_.index==tempIndex)[0].length;
          let accumulatedLength = indexLength.filter(_=>_.index<tempIndex).map(_=>_.length).reduce((sum, l)=>sum+l, 0);
          setMyResult(history.slice(
            accumulatedLength,
            accumulatedLength+currentLength
          ));
          return;
        }

        // new page
        // loading
        setIsLoading(true);
        const res = (await SearchService.search(keyword, 10, (currentIndex+1)*10)).data['myResult'];
        const originalLength = history.length;
        const addedLength = res.length;
        let tempHis = [...history, ...res];
        setHistory(his => [...his, ...res]);
        setIndexLength([...indexLength, { index: currentIndex+1, length: addedLength }]);
        setCurrentIndex(currentIndex+1);
        setMyResult(tempHis.slice(originalLength, originalLength+addedLength)); // res may not have 10 results
        setIsLoading(false);
        break;
    }
  }

  var searchResView = () => { 
    return myResult.map((data, index)=>{
      return (
        <View 
          style={styles.result}
          key={index}
        >
          <Text 
            style={styles.resultTitle}
            onPress={() => Linking.openURL(data["url"])}
          >
            { data["displayName"] }
          </Text>
          <Text
            style={styles.description}
          >
            { data["description"] }
          </Text>
        </View>
      );
    });
  }

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity
          onPress={ () => onBackToSearch() }
        >
          <Icon name="left" size={25} color="#900" />
        </TouchableOpacity>
        <Text style={
          {
            fontSize: 25, 
            position: 'absolute', 
            width: '50%',
            textAlign: 'center',
            left: '25%', 
          }
        }
        >Result</Text>
      </View>

      <View
        style = {styles.pagination}
      >
        <TouchableOpacity
          onPress={ () => handlePagination('pre') }
          style = {styles.pagBtn}
        >
          <Icon name="left" size={20} color="#900" />
          <Text style={{fontSize: 18}}>Previous</Text>
        </TouchableOpacity> 
        <Text
          style={styles.pageIndex}
        >{currentIndex+1}th page</Text>
        <TouchableOpacity
          onPress={ () => handlePagination('next') }
          style = {[styles.pagBtn, {marginLeft: 'auto'}]}
        >
          <Text style={{fontSize: 18}}>Next</Text>
          <Icon name="right" size={20} color="#900" />
        </TouchableOpacity> 
      </View>
      
      <ScrollView style={styles.resultScrollView}>
        { searchResView() }
      </ScrollView>

      { isLoading ? <LoadingComponent/> : null }
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container:{
    padding: 10,
    width: "100%",
    height: "100%",
    alignItems: 'center',
    justifyContent: 'center'
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'flex-start',
    paddingHorizontal: 10,
    marginVertical: 5,
    height: '8%',
    width: '100%'
  },
  result: {
    alignItems: 'flex-start',
    marginVertical: 20
  },
  resultTitle:{
    fontSize: 17,
    color: 'blue'
  },
  resultScrollView:{
    paddingHorizontal: 30,
    overflow: 'scroll',
  },
  description: {
    fontSize: 15,
    marginTop: 5,
    color: "#252525"
  },
  pagination:{
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 20,
    marginVertical: 3,
    height: '6%',
    width: '100%'
  },
  pagBtn:{
    color: '#000000',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center'
  },
  pageIndex:{
    fontSize: 18,
    marginLeft: 'auto'
  }
});

