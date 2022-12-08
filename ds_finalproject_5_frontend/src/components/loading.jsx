import { View, Text, StyleSheet } from 'react-native'
import React from 'react'

export default function LoadingComponent() {
  return (
    <View
        style={styles.container}
    >
      <Text style={styles.text}>loading...</Text>
    </View>
  )
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: "#303030",
        alignItems: "center",
        justifyContent: "center",
        position: 'absolute',
        top: 0,
        right: 0,
        bottom: 0,
        left: 0,
        zIndex: 10,
        opacity: 0.6
    },
    text:{
        color: "#ffffff",
        fontSize: 20
    }
});