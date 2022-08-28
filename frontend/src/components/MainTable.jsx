import { useEffect, useState } from "react";
import { Link } from "react-router-dom";


const callRestApi = async () => {
    const response = await fetch("http://localhost:8080/api/v1/manage");
    const jsonResponse = await response.json();

    const arrayOfLists = jsonResponse.map(
        element => <tr key={element.shortUrl}>
            <td>{element.shortUrl}</td>
            <td> <Link to={element.shortUrl}>Click Here</Link> </td>
        </tr>
    )
    return arrayOfLists;
};


function MainTable() {

    const [apiResponse, setApiResponse] = useState("*** now loading ***");

    useEffect(() => {
        callRestApi().then(
            result => setApiResponse(result));
    }, []);

    return (

        <div className="mt-3">
            <table className="table">
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">Short URL</th>
                        <th scope="col">More Info</th>
                    </tr>
                </thead>
                <tbody>
                    {apiResponse}
                </tbody>
            </table>
        </div>

    )
}

export default MainTable