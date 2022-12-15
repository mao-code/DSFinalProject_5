import { serverConfig } from '../config.js';
import axios from 'axios';

const SearchService = {
    search: async function(keyword, count=20, skip=0){
        // fetch search api in java spring boot
        let { domain, port, route, category, action } = serverConfig;
        let uri = `http://${domain}:${port}/${route.search}/${category.movie}/${action.search}/${keyword}/${count}/${skip}`;
        try{
            const res = await axios.get(uri);
            return res.data;
        }catch(error){
            console.log(error);
        }
             
    }
};

export default SearchService;