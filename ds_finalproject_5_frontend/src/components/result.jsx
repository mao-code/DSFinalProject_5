import { View, Text, StyleSheet, Linking, SafeAreaView, TouchableOpacity, ScrollView } from 'react-native'
import React, { useState } from 'react';
import Icon from 'react-native-vector-icons/AntDesign';

export default function ResultComponent({searchRes, onBackToSearch}) {
  const [input, setInput] = useState(searchRes);
  const myResult = input["myResult"];
  const orginalGoogleResult = input["orginalGoogleResult"];

  var searchResView = myResult.map(data=>{
    return (
      <View 
        style={styles.result}
        key={data["url"]}
      >
        <Text 
          style={styles.resultTitle}
          onPress={() => Linking.openURL(data["url"])}
        >
          { data["displayName"] }
        </Text>
      </View>
    );
  });

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
      <ScrollView style={styles.resultScrollView}>
        { searchResView }
      </ScrollView>
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
    alignItems: 'center',
    marginVertical: 20
  },
  resultTitle:{
    fontSize: 17,
    color: 'blue'
  },
  resultScrollView:{
    paddingHorizontal: 15,
    overflow: 'scroll',
  }
});

