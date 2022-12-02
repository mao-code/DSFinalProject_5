import React, { useState } from 'react';
import { Text, StyleSheet, TextInput, View, Button, TouchableOpacity } from 'react-native';

const SearchComponent = () => {
  const [keyword, setKeyword] = useState('');
  const handleSearch = () => {
    //fetch('',{}); // fetch search api in java spring boot
    console.log(keyword);
  };

  return (
    <View style={ styles.container }>
        <View style={ styles.logoContainer }> 
          <Text style={[styles.logoText, {color:'#4485f5'}]}>G</Text>
          <Text style={[styles.logoText, {color:'#ea4234'}]}>o</Text>
          <Text style={[styles.logoText, {color:'#fbbd05'}]}>o</Text>
          <Text style={[styles.logoText, {color:'#4485f5'}]}>g</Text>
          <Text style={[styles.logoText, {color:'#34a854'}]}>1</Text>
          <Text style={[styles.logoText, {color:'#ea4234'}]}>e</Text>
        </View>
        <TextInput
            style={ styles.textInput }
            placeholder="Please input some keywords"
            onChangeText={newKeyword => setKeyword(newKeyword)}
            defaultValue={keyword}
        />
        <TouchableOpacity 
          style={ styles.searchBtn }
          onPress={ handleSearch }
        >
          <Text style={{color:'#3c4043', fontSize: 16}}>Goog1e Search</Text>
        </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
    container:{
      alignItems: 'center',
      justifyContent: 'center',
      padding: 10
    },
    textInput: {
      height: 50,
      width: 300,
      borderWidth: 1,
      borderRadius: 5,
      paddingHorizontal: 10
    },
    logoContainer:{
      flexDirection: 'row',
      alignItems: 'center', 
      justifyContent: 'center',
      marginBottom: 40
    },
    logoText:{
      fontSize: 45,
      fontWeight: '600',
      textAlign: 'center',
    },
    searchBtn:{
      backgroundColor:'#f8f9fa',
      paddingHorizontal: 15,
      paddingVertical: 10,
      marginTop: 15
    }
});

export default SearchComponent;