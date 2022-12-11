import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import SearchComponent from './src/components/search';
import { getLocales } from "expo-localization";
import React, { useState } from 'react';
import ResultComponent from './src/components/result';

export default function App() {
  const [searchRes, setSearchRes] = useState(null);
  const [isShowResult, setIsShowResult] = useState(false);

  const onSearchRes = (data) => {
    setSearchRes(data);
    setIsShowResult(true);
  }

  const onBackToSearch = () => {
    setIsShowResult(false);
  }

  return (
    <View style={styles.container}>
      
      { 
        isShowResult ? 
        <ResultComponent
          style={styles.result}
          searchRes={searchRes}
          onBackToSearch={onBackToSearch}
        /> : 
        <SearchComponent 
          style={styles.search}
          onSearchRes={onSearchRes}
        />
      }
     
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
    position: 'absolute',
    top: 0,
    right: 0,
    bottom: 0,
    left: 0
  },
  search: {
    position: 'absolute',
    top: 0,
    right: 0,
    bottom: 0,
    left: 0
  },
  result: {
    position: 'absolute',
    top: 0,
    right: 0,
    bottom: 0,
    left: 0
  }
});
