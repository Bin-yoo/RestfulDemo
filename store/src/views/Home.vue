<template>
    <div class="home">
        <Row>
            <Col span="12">
                <Card style="">
                    <p slot="title">
                        <Icon type="ios-film-outline"></Icon>
                        登录
                    </p>
                    <Form ref="formInline" inline>
                        <FormItem prop="user">
                            <Input type="text" v-model="userName" placeholder="Username">
                                <Icon type="ios-person-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="password" placeholder="Password">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button type="primary" @click="Login()">Signin</Button>
                        </FormItem>
                    </Form>
                </Card>
            </Col>
            <Col span="12">
                <Card style="">
                    <p slot="title">
                        <Icon type="ios-film-outline"></Icon>
                        注册
                    </p>
                    <Form ref="formInline" :model="tblCustomer">
                        <FormItem prop="user">
                            <Input type="text" v-model="tblCustomer.customerName" placeholder="用户名">
                                <Icon type="ios-person-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="tblCustomer.password" placeholder="密码">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="customerTrueName">
                            <Input type="text" v-model="tblCustomer.customerTrueName" placeholder="个人姓名">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="customerAddress">
                            <Input type="text" v-model="tblCustomer.customerAddress" placeholder="个人地址">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="sex">
                            <RadioGroup v-model="tblCustomer.sex">
                                <Radio label="1">
                                    <Icon type="ios-man"></Icon>
                                    <span>男</span>
                                </Radio>
                                <Radio label="0">
                                    <Icon type="ios-woman"></Icon>
                                    <span>女</span>
                                </Radio>
                            </RadioGroup>
                        </FormItem>
                        <FormItem prop="customerEmail">
                            <Input type="email" v-model="tblCustomer.customerEmail" placeholder="电子邮箱">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="customerPhone">
                            <Input type="text" v-model="tblCustomer.customerPhone" placeholder="个人电话">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button type="primary" @click="Register()">注册</Button>
                        </FormItem>
                    </Form>
                </Card>
            </Col>
        </Row>
        <Row>
            <Col span="24">
                <Table :columns="columns1" :data="customerList">
                    <template slot-scope="{ row, index }" slot="action">
                        <Button type="info" size="small" @click="beforeModify(index),modal = true">修改</Button>
                        <Button type="error" size="small" @click="remove(index,row.customerID)">删除</Button>
                    </template>
                </Table>
            </Col>
        </Row>
        <Row>
            <Col span="12">
                <Modal v-model="modal" title="修改" @on-ok="modify" :mask-closable="false">
                    <Form ref="formInline" :model="customer">
                        <FormItem prop="user">
                            <Input type="text" v-model="customer.customerName" placeholder="用户名">
                                <Icon type="ios-person-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="customer.password" placeholder="密码">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="customerTrueName">
                            <Input type="text" v-model="customer.customerTrueName" placeholder="个人姓名">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="customerAddress">
                            <Input type="text" v-model="customer.customerAddress" placeholder="个人地址">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="sex">
                            <RadioGroup v-model="customer.sex">
                                <Radio label="1">
                                    <Icon type="ios-man"></Icon>
                                    <span>男</span>
                                </Radio>
                                <Radio label="0">
                                    <Icon type="ios-woman"></Icon>
                                    <span>女</span>
                                </Radio>
                            </RadioGroup>
                        </FormItem>
                        <FormItem prop="customerEmail">
                            <Input type="email" v-model="customer.customerEmail" placeholder="电子邮箱">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="customerPhone">
                            <Input type="text" v-model="customer.customerPhone" placeholder="个人电话">
                                <Icon type="md-body" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                    </Form>
                </Modal>
            </Col>
        </Row>
    </div>
</template>

<script>
export default {
    name: 'home',
    data(){
        return{
            userName:'',
            password:'',
            tblCustomer:{
                customerName:'',
                password:'',
                customerTrueName:'',
                customerAddress:'',
                sex:'1',
                customerEmail:'',
                customerPhone:'',
            },
            customer:{},
            columns1: [
                {
                    title: 'CustomerName',
                    key: 'customerName'
                },
                {
                    title: 'CustomerTrueName',
                    key: 'customerTrueName'
                },
                {
                    title: 'CustomerAddress',
                    key: 'customerAddress'
                },
                {
                    title: 'Sex',
                    key: 'sex'
                },
                {
                    title: 'CustomerEmail',
                    key: 'customerEmail'
                },
                {
                    title: 'CustomerPhone',
                    key: 'customerPhone'
                },
                {
                    title: '操作',
                    slot: 'action'
                }
            ],
            customerList:[],
            modal:false,
        }
    },
    mounted:function(){
        this.getCustomerList();
    },
    methods:{
        Login:function(){
            this.axios({
                method: 'get',
                url: 'http://localhost:8888/test/login',
                data: {
                    userName:this.userName,
                    password:this.password,
                }
            })
            .then(response => {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        Register:function(){
            this.axios({
                method: 'post',
                url: 'http://localhost:8888/test/register',
                data: this.tblCustomer
            })
            .then(response => {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        getCustomerList:function(){
            this.axios({
                method: 'get',
                url: 'http://localhost:8888/test/customer',
            })
            .then(response => {
                console.log(response);
                this.customerList = response.data;
            })
            .catch(function (error) {
                console.log(error);
            });
        },
        remove:function(index,id){
            this.axios({
                method: 'delete',
                url: 'http://localhost:8888/test/customer/' + id,
            })
            .then(response => {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
            this.customerList.splice(index, 1);
        },
        beforeModify:function(index){
            this.customer = this.customerList[index]
        },
        modify:function(){
            this.axios({
                method: 'put',
                url: 'http://localhost:8888/test/customer/' + this.customer.customerID,
                data:this.customer
            })
            .then(response => {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
        },
    }
}
</script>
