/* 后期优化：手动把不需要用到的组件注释掉 */

import Vue from 'vue'
import {
  // Alert,
  // Aside,
  // Autocomplete,
   Avatar,
  // Backtop,
  // Badge,
  // Breadcrumb,
  // BreadcrumbItem,
   Button,
  // ButtonGroup,
  // Calendar,
     Card,
    Empty,
  // Carousel,
  // CarouselItem,
  // Cascader,
  // CascaderPanel,
  // Checkbox,
  // CheckboxButton,
  // CheckboxGroup,
   Col,
  // Collapse,
  // CollapseItem,
  // ColorPicker,
  // Container,
  // DatePicker,
   Dialog,
  // Divider,
  // Drawer,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  // Footer,
   Form,
   FormItem,
  // Header,
   Icon,
   Image,
  // InfiniteScroll,
   Input,
  // InputNumber,
   Link,
  Loading,
  // Main,
   Menu,
   MenuItem,
   MenuItemGroup,
  Message,
  // MessageBox,
  // Notification,
  // Option,
  // OptionGroup,
  // PageHeader,
  // Pagination,
  // Popconfirm,
  // Popover,
  // Progress,
   Radio,
   RadioButton,
   RadioGroup,
   Rate,
   Row,
  // Scrollbar,
  // Select,
  // Slider,
  // Spinner,
  // Step,
  // Steps,
   Submenu,
  // Switch,
  // Table,
  // TableColumn,
   TabPane,
   Tabs,
   Tag,
  // Timeline,
  // TimelineItem,
  // TimePicker,
  // TimeSelect,
  // Tooltip,
  // Transfer,
  // Tree,
  // Upload,
} from 'element-ui'
import './rewrite/index.less'

// Vue.use(Alert)
// Vue.use(Aside)
// Vue.use(Autocomplete)
 Vue.use(Avatar)
// Vue.use(Backtop)
// Vue.use(Badge)
// Vue.use(Breadcrumb)
// Vue.use(BreadcrumbItem)
Vue.use(Button)
// Vue.use(ButtonGroup)
// Vue.use(Calendar)
 Vue.use(Card)
 Vue.use(Empty)
// Vue.use(Carousel)
// Vue.use(CarouselItem)
// Vue.use(Cascader)
// Vue.use(CascaderPanel)
// Vue.use(Checkbox)
// Vue.use(CheckboxButton)
// Vue.use(CheckboxGroup)
 Vue.use(Col)
// Vue.use(Collapse)
// Vue.use(CollapseItem)
// Vue.use(ColorPicker)
// Vue.use(Container)
// Vue.use(DatePicker)
 Vue.use(Dialog)
// Vue.use(Divider)
// Vue.use(Drawer)
 Vue.use(Dropdown)
 Vue.use(DropdownItem)
 Vue.use(DropdownMenu)
// Vue.use(Footer)
 Vue.use(Form)
 Vue.use(FormItem)
// Vue.use(Header)
 Vue.use(Icon)
 Vue.use(Image)
// Vue.use(InfiniteScroll)
 Vue.use(Input)
// Vue.use(InputNumber)
 Vue.use(Link)
Vue.use(Loading.directive)
Vue.prototype.$loading = Loading.service
// Vue.use(Main)
 Vue.use(Menu)
 Vue.use(MenuItem)
 Vue.use(MenuItemGroup)
Vue.prototype.$message = Message
// Vue.prototype.$msgbox = MessageBox
// Vue.prototype.$alert = MessageBox.alert
// Vue.prototype.$confirm = MessageBox.confirm
// Vue.prototype.$prompt = MessageBox.prompt
// Vue.prototype.$notify = Notification
// Vue.use(Option)
// Vue.use(OptionGroup)
// Vue.use(PageHeader)
// Vue.use(Pagination)
// Vue.use(Popconfirm)
// Vue.use(Popover)
// Vue.use(Progress)
 Vue.use(Radio)
 Vue.use(RadioButton)
 Vue.use(RadioGroup)
 Vue.use(Rate)
 Vue.use(Row)
// Vue.use(Scrollbar)
// Vue.use(Select)
// Vue.use(Slider)
// Vue.use(Spinner)
// Vue.use(Step)
// Vue.use(Steps)
 Vue.use(Submenu)
// Vue.use(Switch)
// Vue.use(Table)
// Vue.use(TableColumn)
 Vue.use(TabPane)
 Vue.use(Tabs)
 Vue.use(Tag)
// Vue.use(Timeline)
// Vue.use(TimelineItem)
// Vue.use(TimePicker)
// Vue.use(TimeSelect)
// Vue.use(Tooltip)
// Vue.use(Transfer)
// Vue.use(Tree)
// Vue.use(Upload)
