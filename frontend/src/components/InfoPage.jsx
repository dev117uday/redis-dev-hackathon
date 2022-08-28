import { useEffect, useState } from "react";
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip } from 'recharts';


const callRestApi = async (shortUrl) => {
    const response = await fetch(`http://localhost:8080/api/v1/manage/${shortUrl}`);
    const data = await response.json();
    return data;
};

const callRestTS = async (shortUrl) => {
    const response = await fetch(`http://localhost:8080/api/v1/ts/${shortUrl}`);
    const data = await response.json();

    let filterData = [];

    data.map(element => {
        let temp = {
            value: element.value,
            timestamp: `${new Date(element.timestamp).toLocaleTimeString()}`
        }
        filterData.push(temp);
    });
    return filterData;
};



function MainTable() {

    let params = window.location.pathname.slice(1);

    const [apiResponse, setApiResponse] = useState("*** now loading ***");
    const [tsData, setTsData] = useState();

    useEffect(() => {
        callRestApi(params).then(
            result => {
                setApiResponse(result)
            });

        callRestTS(params).then(
            result => {
                setTsData(result)
            });
    }, []);

    return (
        <>
            <div className="card">
                <div className="card-header">
                    Owned by : {apiResponse.owner}
                </div>
                <div className="card-body">
                    <h5 className="card-title">Short URL : {apiResponse.shortUrl}</h5>
                    <p className="card-text">Long URL : {apiResponse.longUrl}</p>
                    <p className="card-text fs-6 text-muted">Created At : {apiResponse.createdAt}</p>
                    <a href="/"><button className="btn btn-primary">Go Back</button></a>
                </div>
            </div>
            <div className="mt-3">
                <LineChart width={600} height={300} data={tsData} margin={{ top: 5, right: 20, bottom: 5, left: 0 }}>
                    <Line type="monotone" dataKey="value" stroke="#8884d8" />
                    <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
                    <XAxis dataKey="timestamp" />
                    <YAxis />
                    <Tooltip />
                </LineChart>
            </div>

        </>


    )
}

export default MainTable